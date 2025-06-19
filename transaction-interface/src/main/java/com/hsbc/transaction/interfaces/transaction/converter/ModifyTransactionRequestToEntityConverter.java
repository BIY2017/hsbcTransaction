package com.hsbc.transaction.interfaces.transaction.converter;

import com.hsbc.tansaction.domian.transaction.entity.Transaction;
import com.hsbc.transaction.interfaces.transaction.dto.CreateTransactionRequest;
import com.hsbc.transaction.interfaces.transaction.dto.ModifyTransactionRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * CreateTransactionRequestToEntityConverter
 *
 * @author Lei
 * @date 2025/6/19 21:44
 */
@Mapper
public interface ModifyTransactionRequestToEntityConverter {

    ModifyTransactionRequestToEntityConverter INSTANCE = Mappers.getMapper(ModifyTransactionRequestToEntityConverter.class);

    Transaction convert(ModifyTransactionRequest modifyTransactionRequest);
}
