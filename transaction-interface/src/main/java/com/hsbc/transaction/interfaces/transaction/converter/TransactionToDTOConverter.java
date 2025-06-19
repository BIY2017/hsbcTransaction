package com.hsbc.transaction.interfaces.transaction.converter;

import com.hsbc.tansaction.domian.transaction.entity.Transaction;
import com.hsbc.transaction.interfaces.transaction.dto.TransactionDTO;
import org.mapstruct.Mapper;
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

    TransactionDTO convert(Transaction transaction);
}
