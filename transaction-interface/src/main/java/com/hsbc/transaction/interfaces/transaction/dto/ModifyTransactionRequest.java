package com.hsbc.transaction.interfaces.transaction.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "金额不能为空")
    @DecimalMin(value = "0", inclusive = false, message = "金额必须大于0")
    private String amount;

    private String remark;

}
