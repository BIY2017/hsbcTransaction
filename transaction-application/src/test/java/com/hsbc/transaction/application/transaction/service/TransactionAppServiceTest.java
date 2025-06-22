package com.hsbc.transaction.application.transaction.service;

import com.hsbc.transaction.domian.account.entity.Account;
import com.hsbc.transaction.domian.account.service.AccountService;
import com.hsbc.transaction.domian.transaction.entity.Transaction;
import com.hsbc.transaction.domian.transaction.entity.enmus.TransactionStatus;
import com.hsbc.transaction.domian.transaction.entity.enmus.TransactionType;
import com.hsbc.transaction.domian.transaction.repository.TransactionRepository;
import com.hsbc.transaction.domian.transaction.service.TransactionService;
import com.hsbc.transaction.domian.transaction.validator.TransactionValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TransactionAppServiceTest
 *
 * @author Lei
 * @date 2025/6/22 2:19
 */
@ExtendWith(MockitoExtension.class)
public class TransactionAppServiceTest {

    @InjectMocks
    private TransactionAppService target;

    @Mock
    private TransactionValidator transactionValidator;

    @Mock
    private TransactionService transactionService;

    @Mock
    private AccountService accountService;

    @Mock
    private TransactionRepository transactionRepository;

    @Test
    @DisplayName("测试正常创建交易")
    void createTransaction_success() {
        Mockito.when(accountService.getCurrentAccount()).thenReturn(mockCurrentAccount());
        Transaction request = new Transaction();
        request.setAmount(BigDecimal.ONE);
        request.setTransactionType(TransactionType.DEPOSIT);
        Transaction realResult = target.createTransaction("1", request);
        Assertions.assertEquals(realResult.getAccountId(), 1L);
        Mockito.verify(transactionService, Mockito.times(1)).createTransaction(Mockito.any());
    }

    private Account mockCurrentAccount() {
        Account account = new Account();
        account.setAccountId(1L);
        account.setAvailableAmount(new BigDecimal("100000"));
        account.setUserId(1L);
        return account;
    }

    @Test
    @DisplayName("测试正常修改交易")
    void modifyTransaction_success() {
        Mockito.when(accountService.getCurrentAccount()).thenReturn(mockCurrentAccount());

        Transaction exist = new Transaction();
        exist.setAmount(BigDecimal.TEN);
        Mockito.when(transactionRepository.getTransactionById(Mockito.anyLong(), Mockito.anyLong())).thenReturn(exist);

        Transaction request = new Transaction();
        request.setAmount(BigDecimal.ONE);
        request.setTransactionId(1003L);
        Transaction realResult = target.modifyTransaction("1", request);
        Assertions.assertEquals(realResult.getAccountId(), 1L);
        Mockito.verify(transactionRepository, Mockito.times(1)).modifyTransaction(Mockito.any());
    }

    @Test
    @DisplayName("测试正常删除交易")
    void removeTransaction_success() {
        Transaction exist = new Transaction();
        exist.setAmount(BigDecimal.TEN);
        Mockito.when(transactionRepository.getTransactionById(Mockito.anyLong(), Mockito.anyLong())).thenReturn(exist);

        target.removeTransaction("1", 1001L);
        Mockito.verify(transactionRepository, Mockito.times(1)).removeTransaction(Mockito.any());
    }
}