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

    private Integer pageNo;

    private Integer pageSize;
}
