package com.hsbc.transaction.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * MyLock
 *
 * @author Lei
 * @date 2025/6/19 19:16
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface MyLock {

    /**
     * 最大加锁时长，单位：秒
     */
    int maxLockTime() default 60;

    /**
     * 加锁key
     */
    String lockKey();
}
