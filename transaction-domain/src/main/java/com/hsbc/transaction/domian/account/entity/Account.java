package com.hsbc.transaction.domian.account.entity;

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

    /**
     * 账号id
     */
    private Long accountId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 可用余额
     */
    private BigDecimal availableAmount;

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
}
