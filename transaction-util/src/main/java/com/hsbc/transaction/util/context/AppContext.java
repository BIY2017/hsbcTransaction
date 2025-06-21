package com.hsbc.transaction.util.context;

/**
 * 模拟用户登录上下文
 *
 * @author Lei
 * @date 2025/6/19 22:32
 */
public class AppContext {

    /**
     * 获取登录账号id
     *
     * @return 用户登录账号
     */
    public static Long getAccountId() {
        return 1L;
    }

    /**
     * 模拟用户登录上下文
     *
     * @return 登录用户id
     */
    public static Long getUserId() {
        return 1L;
    }
}
