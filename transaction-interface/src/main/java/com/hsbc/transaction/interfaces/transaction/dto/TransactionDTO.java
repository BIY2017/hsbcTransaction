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

    private String transactionId;

    private String accountId;

    private String amount;

    private String transactionTime;

    private String remark;

    private String transactionType;

    private String transactionStatus;

    private String fee;
}
