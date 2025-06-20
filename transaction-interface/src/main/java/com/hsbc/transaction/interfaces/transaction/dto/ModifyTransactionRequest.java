package com.hsbc.transaction.interfaces.transaction.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ModifyTransactionRequest
 *
 * @author Lei
 * @date 2025/6/19 23:04
 */
@Getter
@Setter
@NoArgsConstructor
public class ModifyTransactionRequest {

    @NotBlank(message = "交易主键不能为空")
    private String transactionId;

    private String accountId;

    private String amount;

    private String remark;
}
