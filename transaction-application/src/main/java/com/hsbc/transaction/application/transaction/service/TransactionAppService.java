package com.hsbc.transaction.application.transaction.service;

import com.hsbc.tansaction.domian.transaction.entity.Transaction;
import com.hsbc.tansaction.domian.transaction.query.TransactionQuery;
import com.hsbc.tansaction.domian.transaction.repository.TransactionRepository;
import com.hsbc.tansaction.domian.transaction.service.TransactionService;
import com.hsbc.transaction.util.annotation.MyLock;
import com.hsbc.transaction.util.response.MyPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TransactionAppService
 *
 * @author Lei
 * @date 2025/6/18 23:04
 */
@Service
public class TransactionAppService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionService transactionService;

    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.createTransaction(transactionService
                .createTransaction(transaction));
    }

    @MyLock(lockKey = "#lockKey")
    public Transaction modifyTransaction(String lockKey, Transaction transaction) {
        return transactionRepository.modifyTransaction(transaction);
    }

    @MyLock(lockKey = "#lockKey")
    public int removeTransaction(String lockKey, Long transactionId) {
        return transactionRepository.removeTransaction(transactionId);
    }

    public MyPage<Transaction> getTransactionsByQuery(TransactionQuery transactionQuery) {
        return transactionRepository.getTransactionsByQuery(transactionQuery);
    }
}
