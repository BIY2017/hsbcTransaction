package com.hsbc.transaction.domian.transaction.repository;

import com.hsbc.transaction.domian.transaction.entity.Transaction;
import com.hsbc.transaction.domian.transaction.query.TransactionQuery;
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

    /**
     * 修改交易
     *
     * @param transaction 待修改的交易
     * @return 修改后的交易
     */
    Transaction modifyTransaction(Transaction transaction);

    /**
     * 删除交易
     *
     * @param transactionId 交易主键
     */
    void removeTransaction(Long transactionId);

    /**
     * 分页查询交易
     *
     * @param transactionQuery 查询参数
     * @return 查询的分页结果
     */
    MyPage<Transaction> getTransactionsByQuery(TransactionQuery transactionQuery);

    /**
     * 根据交易主键id查询交易
     *
     * @param accountId 账户主键id
     * @param transactionId 交易主键id
     * @return 交易
     */
    Transaction getTransactionById(Long accountId, Long transactionId);
}
