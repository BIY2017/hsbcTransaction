package com.hsbc.transaction.domian.transaction.service;

import com.hsbc.transaction.domian.transaction.constant.TransactionConstants;
import com.hsbc.transaction.domian.transaction.entity.Transaction;
import com.hsbc.transaction.domian.transaction.repository.TransactionRepository;
import org.redisson.api.RIdGenerator;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TransactionService
 *
 * @author Lei
 * @date 2025/6/19 13:40
 */

@Service
public class TransactionService {

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private TransactionRepository transactionRepository;

    /**
     * 创建交易
     *
     * @param transaction 待创建的交易
     * @return 创建的交易
     */
    public Transaction createTransaction(Transaction transaction) {
        transaction.create();
        RIdGenerator idGenerator = redissonClient.getIdGenerator(TransactionConstants.TRANSACTION_ID_NAME);
        transaction.setTransactionId(idGenerator.nextId());
        transactionRepository.createTransaction(transaction);
        return transaction;
    }
}
