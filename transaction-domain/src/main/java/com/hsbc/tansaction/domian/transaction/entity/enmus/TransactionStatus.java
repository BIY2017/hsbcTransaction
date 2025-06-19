package com.hsbc.tansaction.domian.transaction.entity.enmus;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * TransactionStatus
 *
 * @author Lei
 * @date 2025/6/19 13:18
 */

@AllArgsConstructor
@Getter
public enum TransactionStatus {

    /**
     * 成功
     */
    SUCCESS(0, "存款"),

    /**
     * 失败
     */
    FAILED(1, "取款"),

    /**
     * 待处理
     */
    PENDING(2, "取款");

    private final Integer code;

    private final String message;
}
