package com.hsbc.tansaction.domian.transaction.query;

import com.hsbc.tansaction.domian.transaction.entity.enmus.TransactionStatus;
import com.hsbc.tansaction.domian.transaction.entity.enmus.TransactionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * TransactionQuery
 *
 * @author Lei
 * @date 2025/6/19 22:11
 */

@NoArgsConstructor
@Getter
@Setter
public class TransactionQuery {

    private List<String> transactionType;

    private List<String> transactionStatus;

    private Integer pageNo;

    private Integer pageSize;
}
