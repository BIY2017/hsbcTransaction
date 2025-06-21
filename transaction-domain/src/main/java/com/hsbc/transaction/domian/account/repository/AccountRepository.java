package com.hsbc.transaction.domian.account.repository;

import com.hsbc.transaction.domian.account.entity.Account;

/**
 * AccountRepository
 *
 * @author Lei
 * @date 2025/6/21 22:40
 */
public interface AccountRepository {

    /**
     * 根据账户id获取账户
     *
     * @param accountId 账户id
     * @return 账户
     */
    Account getAccountById(Long accountId);

    /**
     * 修改账户
     *
     * @param account 账户
     * @return 修改账户
     */
    Account modifyAccount(Account account);
}
