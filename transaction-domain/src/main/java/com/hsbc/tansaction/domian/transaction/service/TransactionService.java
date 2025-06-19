package com.hsbc.tansaction.domian.transaction.service;

import com.hsbc.tansaction.domian.transaction.entity.Transaction;
import com.hsbc.tansaction.domian.transaction.repository.TransactionRepository;
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

    public Transaction createTransaction(Transaction transaction) {
        transaction.validateAmount();
        transaction.init();
        return transaction;
    }
}
