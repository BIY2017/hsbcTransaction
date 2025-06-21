package com.hsbc.transaction.interfaces.transaction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TransactionDTO
 *
 * @author Lei
 * @date 2025/6/19 21:42
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionDTO {

    /**
     * 交易id
     */
    private String transactionId;

    /**
     * 交易账户id
     */
    private String accountId;

    /**
     * 关联交易账户id
     */
    private String relationAccountId;

    /**
     * 交易类型
     */
    private String transactionType;

    /**
     * 交易状态
     */
    private String transactionStatus;

    /**
     * 交易手续费
     */
    private String fee;

    /**
     * 交易金额
     */
    private String amount;

    /**
     * 交易时间
     */
    private String transactionTime;

    /**
     * 备注
     */
    private String remark;

}
