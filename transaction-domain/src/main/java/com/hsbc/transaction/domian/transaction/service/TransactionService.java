package com.hsbc.transaction.domian.transaction.service;

import com.hsbc.transaction.domian.account.repository.AccountRepository;
import com.hsbc.transaction.domian.transaction.TransactionConstants;
import com.hsbc.transaction.domian.transaction.entity.Transaction;
import com.hsbc.transaction.domian.transaction.entity.enmus.TransactionType;
import com.hsbc.transaction.domian.transaction.repository.TransactionRepository;
import jakarta.annotation.PostConstruct;
import org.redisson.Redisson;
import org.redisson.api.RIdGenerator;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

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
        return transactionRepository.createTransaction(transaction);
    }
}
