package com.hsbc.transaction.interfaces.transaction.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * TransactionQueryDTO
 *
 * @author Lei
 * @date 2025/6/19 22:47
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionQueryDTO {

    /**
     * 页码
     */
    private Integer pageNo;

    /**
     * 分页大小
     */
    private Integer pageSize;
}
