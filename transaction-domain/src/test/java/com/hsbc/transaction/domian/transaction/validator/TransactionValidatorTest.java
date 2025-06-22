package com.hsbc.transaction.domian.transaction.validator;

import com.hsbc.transaction.domian.account.entity.Account;
import com.hsbc.transaction.domian.transaction.entity.Transaction;
import com.hsbc.transaction.domian.transaction.entity.enmus.TransactionType;
import com.hsbc.transaction.util.exception.ErrorCode;
import com.hsbc.transaction.util.exception.MyException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * targetTest
 *
 * @author Lei
 * @date 2025/6/22 13:51
 */
@ExtendWith(MockitoExtension.class)
class targetTest {

    @Mock
    private Account account;

    @Mock
    private Transaction newTransaction;

    @Mock
    private Transaction oldTransaction;

    @InjectMocks
    private TransactionValidator target;

    @BeforeEach
    void setUp() {
        // 重置验证器映射
        ReflectionTestUtils.setField(target, "createTransactionTypeToValidatorMap", new HashMap<>());
        ReflectionTestUtils.setField(target, "modifyTransactionTypeToValidatorMap", new HashMap<>());
        // 重新初始化映射
        target.initValidatorMap();
    }

    @Test
    @DisplayName("测试校验交易不存在")
    public void validateTransactionExist_notExist() {
        MyException myException = assertThrows(MyException.class, () -> target.validateTransactionExist(null));
        Assertions.assertEquals(ErrorCode.TRANSACTION_NOT_EXIST, myException.getErrorCode());
    }

    @Test
    @DisplayName("测试创建取款交易校验不通过_余额不足")
    void validateCreate_withdrawal_shouldThrowWhenInsufficientFunds() {
        // 设置
        Mockito.when(newTransaction.getTransactionType()).thenReturn(TransactionType.WITHDRAWAL);
        Mockito.when(newTransaction.getAccount()).thenReturn(account);
        Mockito.when(account.getAvailableAmount()).thenReturn(BigDecimal.valueOf(100));
        Mockito.when(newTransaction.getAmount()).thenReturn(BigDecimal.valueOf(150));

        // 执行和验证
        MyException exception = assertThrows(MyException.class,
                () -> target.validateCreate(newTransaction));

        assertEquals(ErrorCode.AMOUNT_NOT_SUFFICIENT, exception.getErrorCode());
    }

    @Test
    @DisplayName("测试创建取款交易校验通过")
    void validateCreate_withdrawal_shouldPassWhenSufficientFunds() {
        // 设置
        Mockito.when(newTransaction.getTransactionType()).thenReturn(TransactionType.WITHDRAWAL);
        Mockito.when(newTransaction.getAccount()).thenReturn(account);
        Mockito.when(account.getAvailableAmount()).thenReturn(BigDecimal.valueOf(200));
        Mockito.when(newTransaction.getAmount()).thenReturn(BigDecimal.valueOf(150));

        // 执行和验证
        assertDoesNotThrow(() -> target.validateCreate(newTransaction));
    }

    @Test
    @DisplayName("测试创建存款交易校验通过")
    void validateCreate_deposit_shouldAlwaysPass() {
        // 设置
        Mockito.when(newTransaction.getTransactionType()).thenReturn(TransactionType.DEPOSIT);

        // 执行和验证
        assertDoesNotThrow(() -> target.validateCreate(newTransaction));
    }


    @Test
    @DisplayName("测试修改交易校验不通过_交易不存在")
    void validateModify_shouldThrowWhenOldTransactionNull() {
        // 执行和验证
        MyException exception = assertThrows(MyException.class,
                () -> target.validateModify(newTransaction, null));

        assertEquals(ErrorCode.TRANSACTION_NOT_EXIST, exception.getErrorCode());
    }

    @Test
    @DisplayName("测试修改取款交易校验不通过_余额不足")
    void validateModify_withdrawal_shouldThrowWhenInsufficientFunds() {
        // 设置
        Mockito.when(newTransaction.getTransactionType()).thenReturn(TransactionType.WITHDRAWAL);
        Mockito.when(newTransaction.getAccount()).thenReturn(account);
        Mockito.when(account.getAvailableAmount()).thenReturn(BigDecimal.valueOf(100));
        Mockito.when(newTransaction.getAmount()).thenReturn(BigDecimal.valueOf(150));
        Mockito.when(oldTransaction.getAmount()).thenReturn(BigDecimal.valueOf(49));

        // 执行和验证
        MyException exception = assertThrows(MyException.class,
                () -> target.validateModify(newTransaction, oldTransaction));

        assertEquals(ErrorCode.AMOUNT_NOT_SUFFICIENT, exception.getErrorCode());
    }

    @Test
    @DisplayName("测试修改取款交易校验通过")
    void validateModify_withdrawal_shouldPassWhenSufficientFunds() {
        // 设置
        Mockito.when(newTransaction.getTransactionType()).thenReturn(TransactionType.WITHDRAWAL);
        Mockito.when(newTransaction.getAccount()).thenReturn(account);
        Mockito.when(account.getAvailableAmount()).thenReturn(BigDecimal.valueOf(200));
        Mockito.when(newTransaction.getAmount()).thenReturn(BigDecimal.valueOf(150));
        Mockito.when(oldTransaction.getAmount()).thenReturn(BigDecimal.valueOf(50));

        // 执行和验证
        assertDoesNotThrow(() -> target.validateModify(newTransaction, oldTransaction));
    }

    @Test
    @DisplayName("测试修改存款交易校验通过")
    void validateModify_deposit_shouldAlwaysPass() {
        // 设置
        Mockito.when(newTransaction.getTransactionType()).thenReturn(TransactionType.DEPOSIT);

        // 执行和验证
        assertDoesNotThrow(() -> target.validateModify(newTransaction, oldTransaction));
    }
}