package com.hsbc.transaction.domian.transaction.entity.enmus;

import com.hsbc.transaction.domian.transaction.entity.Transaction;
import com.hsbc.transaction.util.exception.ErrorCode;
import com.hsbc.transaction.util.exception.MyException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

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
    WITHDRAWAL(1, "取款");

    private final Integer code;

    private final String message;

    /**
     * 根据code获取对应枚举
     *
     * @param code code
     * @return code对应的枚举
     */
    public static TransactionType getByCode(Integer code) {
        for (TransactionType value : values()) {
            if (Objects.equals(code, value.code)) {
                return value;
            }
        }
        throw new MyException(ErrorCode.SYSTEM_ERROR);
    }

    /**
     * 交易类型取反
     *
     * @return 取反后的交易类型
     */
    public TransactionType negative() {
        return DEPOSIT == this ? WITHDRAWAL : DEPOSIT;
    }
}
