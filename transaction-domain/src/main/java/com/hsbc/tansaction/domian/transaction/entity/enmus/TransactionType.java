package com.hsbc.tansaction.domian.transaction.entity.enmus;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * TransactionType
 *
 * @author Lei
 * @date 2025/6/19 13:14
 */

@AllArgsConstructor
@Getter
public enum TransactionType {

    /**
     * 存款
     */
    DEPOSIT(0, "存款"),

    /**
     * 取款
     */
    WITHDRAWAL(1, "取款"),

    /**
     * 转账
     */
    TRANSFER(2, "转账");

    private final Integer code;

    private final String message;
}
