package com.hsbc.transaction.interfaces.transaction.dto;

import jakarta.validation.constraints.NotBlank;
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

    private String accountId;

    @NotBlank(message = "交易金额不能空")
    private String amount;

    private String relationAccountId;

    private String remark;

    @NotBlank(message = "交易类型不能为空")
    private String transactionType;
}
