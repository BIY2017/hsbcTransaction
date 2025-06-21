package com.hsbc.transaction.interfaces.transaction.converter;

import com.hsbc.transaction.domian.transaction.entity.Transaction;
import com.hsbc.transaction.domian.transaction.entity.enmus.TransactionType;
import com.hsbc.transaction.interfaces.transaction.dto.CreateTransactionRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * CreateTransactionRequestToEntityConverter
 *
 * @author Lei
 * @date 2025/6/19 21:44
 */
@Mapper
public interface CreateTransactionRequestToEntityConverter {

    CreateTransactionRequestToEntityConverter INSTANCE = Mappers.getMapper(CreateTransactionRequestToEntityConverter.class);

    @Mapping(target = "transactionType", expression = "java(convertTransactionType(createTransactionRequest))")
    Transaction convert(CreateTransactionRequest createTransactionRequest);

    default TransactionType convertTransactionType(CreateTransactionRequest createTransactionRequest) {
        return TransactionType.getByCode(createTransactionRequest.getTransactionType());
    }
}
