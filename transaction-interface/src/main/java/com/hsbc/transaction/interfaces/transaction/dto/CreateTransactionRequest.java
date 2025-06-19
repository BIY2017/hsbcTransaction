package com.hsbc.transaction.interfaces.transaction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CreateTransactionRequest
 *
 * @author Lei
 * @date 2025/6/19 13:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTransactionRequest {

    private String transactionId;

    private String accountId;

    private String amount;

    private String transactionTime;

    private String remark;

    private String transactionType;
}
