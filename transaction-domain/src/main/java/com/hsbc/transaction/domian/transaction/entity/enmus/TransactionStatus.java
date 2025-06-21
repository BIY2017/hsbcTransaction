package com.hsbc.transaction.domian.transaction.entity.enmus;

import com.hsbc.transaction.util.exception.ErrorCode;
import com.hsbc.transaction.util.exception.MyException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

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

    /**
     * 根据code获取对应枚举
     *
     * @param code code
     * @return code对应的枚举
     */
    public static TransactionStatus getByCode(Integer code) {
        for (TransactionStatus value : values()) {
            if (Objects.equals(code, value.code)) {
                return value;
            }
        }
        throw new MyException(ErrorCode.SYSTEM_ERROR);
    }
}
