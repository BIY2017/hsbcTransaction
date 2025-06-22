package com.hsbc.transaction.domian.transaction.validator;

import com.hsbc.transaction.domian.transaction.entity.Transaction;
import com.hsbc.transaction.domian.transaction.entity.enmus.TransactionType;
import com.hsbc.transaction.util.exception.ErrorCode;
import com.hsbc.transaction.util.exception.MyException;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * TransactionValidator
 *
 * @author Lei
 * @date 2025/6/21 22:21
 */
@Service
public class TransactionValidator {

    private final Map<TransactionType, Consumer<Transaction>> createTransactionTypeToValidatorMap = new HashMap<>();

    private final Map<TransactionType, BiConsumer<Transaction, Transaction>> modifyTransactionTypeToValidatorMap = new HashMap<>();

    @PostConstruct
    public void initValidatorMap() {
        createTransactionTypeToValidatorMap.put(TransactionType.DEPOSIT, this::validateDepositCreate);
        createTransactionTypeToValidatorMap.put(TransactionType.WITHDRAWAL, this::validateWithdrawCreate);

        modifyTransactionTypeToValidatorMap.put(TransactionType.DEPOSIT, this::validateDepositModify);
        modifyTransactionTypeToValidatorMap.put(TransactionType.WITHDRAWAL, this::validateWithdrawModify);
    }

    /**
     * 校验交易存在
     *
     * @param transaction 交易
     */
    public void validateTransactionExist(Transaction transaction) {
        if (Objects.isNull(transaction)) {
            throw new MyException(ErrorCode.TRANSACTION_NOT_EXIST);
        }
    }

    /**
     * 根据不同交易类型进行校验
     *
     * @param transaction 交易
     */
    public void validateCreate(Transaction transaction) {
        createTransactionTypeToValidatorMap.get(transaction.getTransactionType()).accept(transaction);
    }

    /**
     * 创建取款校验
     *
     * @param transaction 交易
     */
    private void validateWithdrawCreate(Transaction transaction) {
        if (transaction.getAccount().getAvailableAmount()
                .compareTo(transaction.getAmount()) < 0) {
            throw new MyException(ErrorCode.AMOUNT_NOT_SUFFICIENT);
        }
    }

    /**
     * 创建存款校验
     *
     * @param transaction 交易
     */
    private void validateDepositCreate(Transaction transaction) {
    }

    /**
     * 修改交易校验
     *
     * @param newTransaction 新交易
     * @param oldTransaction 旧交易
     */
    public void validateModify(Transaction newTransaction, Transaction oldTransaction) {
        validateTransactionExist(oldTransaction);
        modifyTransactionTypeToValidatorMap.get(newTransaction.getTransactionType()).accept(newTransaction, oldTransaction);
    }

    /**
     * 取款校验
     *
     * @param newTransaction 新交易
     * @param oldTransaction 旧交易
     */
    private void validateWithdrawModify(Transaction newTransaction, Transaction oldTransaction) {
        if (newTransaction.getAccount().getAvailableAmount().add(oldTransaction.getAmount())
                .compareTo(newTransaction.getAmount()) < 0) {
            throw new MyException(ErrorCode.AMOUNT_NOT_SUFFICIENT);
        }
    }

    /**
     * 存款校验
     *
     * @param newTransaction 新交易
     * @param oldTransaction 旧交易
     */
    private void validateDepositModify(Transaction newTransaction, Transaction oldTransaction) {
    }

}
