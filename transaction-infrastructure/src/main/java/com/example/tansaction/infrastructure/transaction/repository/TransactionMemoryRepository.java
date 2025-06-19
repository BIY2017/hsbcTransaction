package com.example.tansaction.infrastructure.transaction.repository;

import com.hsbc.tansaction.domian.transaction.entity.Transaction;
import com.hsbc.tansaction.domian.transaction.query.TransactionQuery;
import com.hsbc.tansaction.domian.transaction.repository.TransactionRepository;
import com.hsbc.transaction.util.context.AppContext;
import com.hsbc.transaction.util.response.MyPage;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * TransactionMemoryRepository
 *
 * @author Lei
 * @date 2025/6/19 21:52
 */
@Repository
public class TransactionMemoryRepository implements TransactionRepository {

    private Map<Long, Map<Long, Transaction>> transactionMap= new ConcurrentHashMap<>();

    @Override
    public Transaction createTransaction(Transaction transaction) {
        transactionMap.computeIfAbsent(transaction.getAccountId(), (k) -> new ConcurrentHashMap<>())
                .put(transaction.getTransactionId(), transaction);
        return transaction;
    }

    @Override
    public Transaction modifyTransaction(Transaction transaction) {
        return transaction;
    }

    @Override
    public int removeTransaction(Long transactionId) {
        transactionMap.get(AppContext.getAccountId()).remove(transactionId);
        return 1;
    }

    @Override
    public MyPage<Transaction> getTransactionsByQuery(TransactionQuery transactionQuery) {
        Long accountId = AppContext.getAccountId();
        Map<Long, Transaction> transactionIdToEntityMap = transactionMap.get(accountId);
        List<Transaction> values = new ArrayList<>(transactionIdToEntityMap.values());
        return MyPage.of((long) ((values.size() - 1) / transactionQuery.getPageSize() + 1), (long) values.size(), transactionQuery.getPageNo(), transactionQuery.getPageSize(), values);
    }
}
