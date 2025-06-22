package com.hsbc.tansaction.domian.transaction;

import com.hsbc.transaction.domian.transaction.entity.Transaction;
import com.hsbc.transaction.domian.transaction.entity.enmus.TransactionStatus;
import com.hsbc.transaction.domian.transaction.repository.TransactionRepository;
import com.hsbc.transaction.domian.transaction.service.TransactionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.redisson.api.RIdGenerator;
import org.redisson.api.RedissonClient;

import java.math.BigDecimal;

/**
 * TransactionServiceTest
 *
 * @author Lei
 * @date 2025/6/22 13:34
 */
@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @InjectMocks
    private TransactionService target;

    @Mock
    private RedissonClient redissonClient;

    @Mock
    private TransactionRepository transactionRepository;

    @Test
    @DisplayName("测试正常创建交易")
    public void testCreateTransaction_success() {
        RIdGenerator mockRIdGenerator = Mockito.mock(RIdGenerator.class);
        Mockito.when(redissonClient.getIdGenerator(Mockito.anyString())).thenReturn(mockRIdGenerator);
        Mockito.when(mockRIdGenerator.nextId()).thenReturn(1001L);
        Transaction request = new Transaction();
        request.setAmount(BigDecimal.ONE);
        request.setAccountId(1L);
        Transaction realResult = target.createTransaction(request);
        Assertions.assertEquals(TransactionStatus.SUCCESS, realResult.getTransactionStatus());
        Assertions.assertEquals(1001L, realResult.getTransactionId());
    }
}
