package com.hsbc.tansaction.domian.transaction.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Account
 *
 * @author Lei
 * @date 2025/6/19 13:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    private Long accountId;

    private Long userId;

    private BigDecimal canUseAmount;


    private LocalDateTime creationTime;

    private Long creationUserId;

    private LocalDateTime updateTime;

    private Long updateUserId;
}
