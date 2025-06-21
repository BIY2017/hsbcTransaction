package com.hsbc.transaction.domian.transaction.entity;

import com.hsbc.transaction.domian.account.entity.Account;
import com.hsbc.transaction.domian.transaction.entity.enmus.TransactionStatus;
import com.hsbc.transaction.domian.transaction.entity.enmus.TransactionType;
import com.hsbc.transaction.util.context.AppContext;
import com.hsbc.transaction.util.exception.ErrorCode;
import com.hsbc.transaction.util.exception.MyException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Transaction
 *
 * @author Lei
 * @date 2025/6/18 22:36
 */
@Data
@NoArgsConstructor
public class Transaction {

    /**
     * 交易id
     */
    private Long transactionId;

    /**
     * 交易账户id
     */
    private Long accountId;

    /**
     * 交易账户
     */
    private Account account;

    /**
     * 关联交易账户id
     */
    private Long relationAccountId;

    /**
     * 关联交易id
     */
    private Long relationTransactionId;

    /**
     * 交易类型
     */
    private TransactionType transactionType;

    /**
     * 交易状态
     */
    private TransactionStatus transactionStatus;

    /**
     * 交易手续费
     */
    private BigDecimal fee;

    /**
     * 交易金额
     */
    private BigDecimal amount;

    /**
     * 交易时间
     */
    private LocalDateTime transactionTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * IT字段，创建时间
     */
    private LocalDateTime creationTime;

    /**
     * IT字段，创建人
     */
    private Long creationUserId;

    /**
     * IT字段，更新时间
     */
    private LocalDateTime updateTime;

    /**
     * IT字段，更新人
     */
    private Long updateUserId;

    /**
     * 创建交易初始化字段
     */
    public void create() {
        this.creationTime = LocalDateTime.now();
        this.updateTime = LocalDateTime.now();
        this.creationUserId = AppContext.getUserId();
        this.updateUserId = AppContext.getUserId();
        this.transactionStatus = TransactionStatus.SUCCESS;
        this.accountId = AppContext.getAccountId();
        this.transactionTime = LocalDateTime.now();
    }
}
