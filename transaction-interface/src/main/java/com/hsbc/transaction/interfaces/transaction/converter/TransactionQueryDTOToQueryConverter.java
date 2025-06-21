package com.hsbc.transaction.interfaces.transaction.converter;

import com.hsbc.transaction.domian.transaction.query.TransactionQuery;
import com.hsbc.transaction.interfaces.transaction.dto.TransactionQueryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * TransactionQueryDTOToQueryConverter
 *
 * @author Lei
 * @date 2025/6/19 22:49
 */
@Mapper
public interface TransactionQueryDTOToQueryConverter {

    TransactionQueryDTOToQueryConverter INSTANCE = Mappers.getMapper(TransactionQueryDTOToQueryConverter.class);

    TransactionQuery convert(TransactionQueryDTO transactionQueryDTO);
}
