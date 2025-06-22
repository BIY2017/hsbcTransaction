package com.hsbc.transaction.util.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ErrorCodeEnum
 *
 * @author Lei
 * @date 2025/6/18 19:54
 */

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /**
     * 成功
     */
    SUCCESS("00000", "成功"),

    /**
     * 系统执行错误
     */
    SYSTEM_ERROR("00001", "系统执行错误"),

    /**
     * 可用余额不足
     */
    AMOUNT_NOT_SUFFICIENT("A0001", "可用余额不足"),

    /**
     * 重复请求
     */
    REPEAT_REQUEST("C0001", "重复请求"),

    /**
     * 非法请求
     */
    ILLEGAL_REQUEST("C0002", "非法请求"),

    /**
     * 交易不存在
     */
    TRANSACTION_NOT_EXIST("C0003", "交易不存在"),

    /**
     * token过期
     */
    TOKEN_EXPIRED("C0004", "token过期，请刷新页面");

    private final String code;

    private final String message;

}
