package com.hsbc.transaction.util.aop;

import com.hsbc.transaction.util.annotation.Idempotent;
import com.hsbc.transaction.util.exception.ErrorCode;
import com.hsbc.transaction.util.exception.MyException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * IdempotentAspect
 *
 * @author Lei
 * @date 2025/6/19 20:26
 */

@Aspect
@Component
public class IdempotentAspect {

    @Autowired
    private RedissonClient redissonClient;

    @Around("@annotation(idempotent)")
    public Object around(ProceedingJoinPoint joinPoint, Idempotent idempotent) throws Throwable {
        // 获取请求
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        HttpServletRequest request = attributes.getRequest();

        // 获取token
        String token = request.getHeader("idemp-token");
        if (StringUtils.isEmpty(token)) {
            throw new MyException(ErrorCode.ILLEGAL_REQUEST);
        }
        // 构造Redis的key
        RBucket<String> bucket = redissonClient.getBucket(token);

        // 删除token（原子删除，避免并发问题）
        boolean isDeleted = bucket.delete();
        // 如果删除成功，说明是第一次提交，执行原方法
        if (isDeleted) {
            return joinPoint.proceed();
        } else {
            throw new MyException(ErrorCode.REPEAT_REQUEST);
        }
    }
}
