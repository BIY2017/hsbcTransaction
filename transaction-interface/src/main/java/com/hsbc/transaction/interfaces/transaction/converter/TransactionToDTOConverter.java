package com.hsbc.transaction.interfaces.transaction.converter;

import com.hsbc.transaction.domian.transaction.entity.Transaction;
import com.hsbc.transaction.interfaces.transaction.dto.TransactionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * TransactionToDTOConverter
 *
 * @author Lei
 * @date 2025/6/19 21:44
 */

@Mapper
public interface TransactionToDTOConverter {

    TransactionToDTOConverter INSTANCE = Mappers.getMapper(TransactionToDTOConverter.class);

    @Mapping(source = "transactionTime", target = "transactionTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    TransactionDTO convert(Transaction transaction);
}
