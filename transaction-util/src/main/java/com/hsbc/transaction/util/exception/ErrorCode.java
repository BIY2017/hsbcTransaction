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

    // 一切OK
    SUCCESS("00000", "成功"),

    AMOUNT_NOT_SUFFICIENT("A0001", "可用余额不足"),

    REPEAT_REQUEST("C0001", "重复请求"),

    ILLEGAL_REQUEST("C0002", "重复请求"),

    SYSTEM_ERROR("B0001", "系统执行错误");


    private final String code;

    private final String message;

}
