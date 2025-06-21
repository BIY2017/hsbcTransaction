package com.hsbc.transaction.interfaces.transaction.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

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

    @NotNull(message = "金额不能为空")
    @DecimalMin(value = "0", inclusive = false, message = "金额必须大于0")
    private BigDecimal amount;

    /**
     * 关联账户id
     */
    private String relationAccountId;

    /**
     * 备注
     */
    private String remark;

    @NotNull(message = "交易类型不能为空")
    private Integer transactionType;
}
