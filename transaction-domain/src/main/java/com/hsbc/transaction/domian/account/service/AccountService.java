package com.hsbc.transaction.domian.account.service;

import com.hsbc.transaction.domian.account.entity.Account;
import com.hsbc.transaction.domian.account.repository.AccountRepository;
import com.hsbc.transaction.domian.transaction.entity.enmus.TransactionType;
import com.hsbc.transaction.util.context.AppContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * AccountService
 *
 * @author Lei
 * @date 2025/6/21 23:28
 */

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    /**
     * 获取当前账户
     *
     * @return 当前账户
     */
    public Account getCurrentAccount() {
        return accountRepository.getAccountById(AppContext.getAccountId());
    }

    /**
     * 执行交易操作
     *
     * @param account 账户
     * @param transactionType 交易类型
     * @param amount 交易金额
     * @return 执行交易后的账户
     */
    public Account doTransaction(Account account, TransactionType transactionType, BigDecimal amount) {
        return account;
    }
}
