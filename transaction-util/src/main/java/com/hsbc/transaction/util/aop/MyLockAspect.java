package com.hsbc.transaction.util.aop;

import com.hsbc.transaction.util.annotation.MyLock;
import com.hsbc.transaction.util.exception.ErrorCode;
import com.hsbc.transaction.util.exception.MyException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MemberSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * 加锁切面
 *
 * @author Lei
 * @date 2025/6/19 19:19
 */
@Component
@Aspect
@Slf4j
public class MyLockAspect {

    @Autowired
    private RedissonClient redissonClient;

    private final ExpressionParser parser = new SpelExpressionParser();

    private final ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();

    @Around("@annotation(myLock)")
    public Object around(ProceedingJoinPoint joinPoint, MyLock myLock) {
        Object[] args = joinPoint.getArgs();
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        String[] parameterNames = parameterNameDiscoverer.getParameterNames(method);
        SimpleEvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().build();
        for (int i = 0; i < args.length; i++) {
            context.setVariable(parameterNames[i], args[i]);
        }
        String lockKey = parser.parseExpression(myLock.lockKey()).getValue(context, String.class);
        RLock lock = redissonClient.getLock(lockKey);
        try {
            boolean isLocked = lock.tryLock(myLock.maxLockTime(), TimeUnit.SECONDS);
            if (isLocked) {
                return joinPoint.proceed();
            } else {
                throw new MyException(ErrorCode.SYSTEM_ERROR);
            }
        } catch (MyException e) {
            throw e;
        } catch (Throwable throwable) {
            log.error(" System Error!", throwable);
            throw new MyException(ErrorCode.SYSTEM_ERROR);
        } finally {
            lock.unlock();
        }
    }
}
