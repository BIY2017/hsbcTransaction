package com.hsbc.transaction.infrastructure.transaction.repository;

import com.hsbc.transaction.domian.transaction.entity.Transaction;
import com.hsbc.transaction.domian.transaction.query.TransactionQuery;
import com.hsbc.transaction.domian.transaction.repository.TransactionRepository;
import com.hsbc.transaction.util.context.AppContext;
import com.hsbc.transaction.util.exception.ErrorCode;
import com.hsbc.transaction.util.exception.MyException;
import com.hsbc.transaction.util.response.MyPage;
import com.hsbc.transaction.util.utils.MyCollectionUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TransactionMemoryRepository
 *
 * @author Lei
 * @date 2025/6/19 21:52
 */
@Repository
public class TransactionMemoryRepository implements TransactionRepository {

    private final Map<Long, Map<Long, Transaction>> accountIdToTransactionMap = new ConcurrentHashMap<>();

    @Override
    public Transaction createTransaction(Transaction transaction) {
        accountIdToTransactionMap.computeIfAbsent(transaction.getAccountId(), (k) -> new ConcurrentHashMap<>())
                .put(transaction.getTransactionId(), transaction);
        return transaction;
    }

    @Override
    public Transaction modifyTransaction(Transaction transaction) {
        accountIdToTransactionMap.get(transaction.getAccountId()).put(transaction.getTransactionId(), transaction);
        return transaction;
    }

    @Override
    public void removeTransaction(Long transactionId) {
        accountIdToTransactionMap.get(AppContext.getAccountId()).remove(transactionId);
    }

    @Override
    public MyPage<Transaction> getTransactionsByQuery(TransactionQuery transactionQuery) {
        Long accountId = AppContext.getAccountId();
        Map<Long, Transaction> transactionIdToEntityMap = accountIdToTransactionMap.get(accountId);
        List<Transaction> values = new ArrayList<>(transactionIdToEntityMap.values());
        return MyCollectionUtils.getPage(values, transactionQuery.getPageNo(), transactionQuery.getPageSize());
    }

    @Override
    public Transaction getTransactionById(Long accountId, Long transactionId) {
        Map<Long, Transaction> transactionIdToTransactionMap = accountIdToTransactionMap.get(accountId);
        if (Objects.isNull(transactionIdToTransactionMap)) {
            throw new MyException(ErrorCode.TRANSACTION_NOT_EXIST);
        }
        return transactionIdToTransactionMap.get(transactionId);
    }
}
