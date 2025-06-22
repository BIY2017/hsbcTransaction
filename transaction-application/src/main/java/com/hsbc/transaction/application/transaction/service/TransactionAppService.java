package com.hsbc.transaction.application.transaction.service;

import com.hsbc.transaction.domian.account.entity.Account;
import com.hsbc.transaction.domian.account.service.AccountService;
import com.hsbc.transaction.domian.transaction.entity.Transaction;
import com.hsbc.transaction.domian.transaction.query.TransactionQuery;
import com.hsbc.transaction.domian.transaction.repository.TransactionRepository;
import com.hsbc.transaction.domian.transaction.service.TransactionService;
import com.hsbc.transaction.domian.transaction.validator.TransactionValidator;
import com.hsbc.transaction.util.annotation.Idempotent;
import com.hsbc.transaction.util.annotation.MyLock;
import com.hsbc.transaction.util.context.AppContext;
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
    private TransactionValidator transactionValidator;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionRepository transactionRepository;

    /**
     * 创建交易（防重复提交，防并发操作）
     *
     * @param lockKey 加锁键（accountId）
     * @param transaction 待创建的交易
     * @return 创建的交易
     */
    @MyLock(lockKey = "#lockKey")
    @Idempotent
    public Transaction createTransaction(String lockKey, Transaction transaction) {
        preProcess(transaction);
        transactionValidator.validateCreate(transaction);
        accountService.doTransaction(transaction.getAccount(), transaction.getTransactionType(), transaction.getAmount());
        transactionService.createTransaction(transaction);
        return transaction;
    }

    private void preProcess(Transaction transaction) {
        Account currentAccount = accountService.getCurrentAccount();
        transaction.setAccount(currentAccount);
        transaction.setAccountId(currentAccount.getAccountId());
    }

    /**
     * 更新交易（防并发操作）
     *
     * @param lockKey 加锁键（accountId）
     * @param transaction 待更新的交易
     * @return 更新后的交易
     */
    @MyLock(lockKey = "#lockKey")
    public Transaction modifyTransaction(String lockKey, Transaction transaction) {
        preProcess(transaction);
        Transaction existedTransaction = transactionRepository.getTransactionById(AppContext.getAccountId(), transaction.getTransactionId());
        transactionValidator.validateModify(transaction, existedTransaction);
        accountService.doTransaction(accountService.getCurrentAccount(), transaction.getTransactionType(), existedTransaction.getAmount()
                .subtract(transaction.getAmount()));
        transactionRepository.modifyTransaction(transaction);
        return transaction;
    }

    /**
     * 删除交易（防并发操作）
     *
     * @param lockKey 加锁键（accountId）
     * @param transactionId 交易主键
     */
    @MyLock(lockKey = "#lockKey")
    public void removeTransaction(String lockKey, Long transactionId) {
        Transaction transaction = transactionRepository.getTransactionById(AppContext.getAccountId(), transactionId);
        transactionValidator.validateTransactionExist(transaction);
        accountService.doTransaction(accountService.getCurrentAccount(), transaction.getTransactionType(), transaction.getAmount());
        transactionRepository.removeTransaction(transactionId);
    }

    /**
     * 分页查询交易列表
     *
     * @param transactionQuery 查询条件
     * @return 分页查询结果
     */
    public MyPage<Transaction> getTransactionsByQuery(TransactionQuery transactionQuery) {
        transactionQuery.setAccountId(AppContext.getAccountId());
        return transactionRepository.getTransactionsByQuery(transactionQuery);
    }
}
