package com.hsbc.transaction.util.aop;

import com.hsbc.transaction.util.annotation.MyLock;
import com.hsbc.transaction.util.exception.ErrorCode;
import com.hsbc.transaction.util.exception.MyException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;

/**
 * MyLockAspect
 *
 * @author Lei
 * @date 2025/6/19 19:19
 */
@Component
@Aspect
public class MyLockAspect {

    @Autowired
    private RedissonClient redissonClient;

    @Around("@annotation(myLock)")
    public Object around(ProceedingJoinPoint joinPoint, MyLock myLock) {
        RLock lock = redissonClient.getLock(myLock.lockKey());
        try {
            boolean isLocked = lock.tryLock(myLock.maxLockTime(), TimeUnit.SECONDS);
            if (isLocked) {
                return joinPoint.proceed();
            } else {
                throw new MyException(ErrorCode.SYSTEM_ERROR);
            }
        } catch (Throwable throwable) {
            throw new MyException(ErrorCode.SYSTEM_ERROR);
        } finally {
            lock.unlock();
        }
    }
}
