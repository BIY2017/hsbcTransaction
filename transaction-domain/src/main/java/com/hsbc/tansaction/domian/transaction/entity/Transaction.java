package com.hsbc.tansaction.domian.transaction.entity;

import com.hsbc.tansaction.domian.transaction.entity.enmus.TransactionStatus;
import com.hsbc.tansaction.domian.transaction.entity.enmus.TransactionType;
import com.hsbc.transaction.util.exception.ErrorCode;
import com.hsbc.transaction.util.exception.MyException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Transaction
 *
 * @author Lei
 * @date 2025/6/18 22:36
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    private Long transactionId;

    private Long accountId;

    private Account account;

    private Long relationAccountId;

    private Account relationAccount;

    private Long relationTransactionId;

    private TransactionType transactionType;

    private TransactionStatus transactionStatus;

    private BigDecimal fee;

    private BigDecimal amount;

    private LocalDateTime transactionTime;

    private String remark;


    private LocalDateTime creationTime;

    private Long creationUserId;

    private LocalDateTime updateTime;

    private Long updateUserId;

    /**
     * 校验交易金额和可用余额
     */
    public void validateAmount() {
        if (account.getCanUseAmount().compareTo(amount) < 0) {
            throw new MyException(ErrorCode.AMOUNT_NOT_SUFFICIENT);
        }
    }

    public void init() {

    }
}
