package com.hsbc.transaction.infrastructure.account.repository;

import com.hsbc.transaction.domian.account.entity.Account;
import com.hsbc.transaction.domian.account.repository.AccountRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * AccountMemoryRepository
 *
 * @author Lei
 * @date 2025/6/21 22:43
 */
@Repository
public class AccountMemoryRepository implements AccountRepository {

    @Override
    public Account getAccountById(Long accountId) {
        Account account = new Account();
        account.setAccountId(1L);
        account.setAvailableAmount(new BigDecimal("100000"));
        account.setUserId(1L);
        return account;
    }

    @Override
    public Account modifyAccount(Account account) {
        return null;
    }
}
