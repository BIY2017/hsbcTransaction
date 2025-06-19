package com.hsbc.tansaction.domian.transaction.repository;

import com.hsbc.tansaction.domian.transaction.entity.Transaction;
import com.hsbc.tansaction.domian.transaction.query.TransactionQuery;
import com.hsbc.transaction.util.response.MyPage;

/**
 * TransactionRepository
 *
 * @author Lei
 * @date 2025/6/19 13:46
 */
public interface TransactionRepository {

    /**
     * 创建交易
     *
     * @param transaction 待保存的交易实体
     * @return 保存后的交易实体
     */
    Transaction createTransaction(Transaction transaction);

    Transaction modifyTransaction(Transaction transaction);

    int removeTransaction(Long transactionId);

    MyPage<Transaction> getTransactionsByQuery(TransactionQuery transactionQuery);


}
