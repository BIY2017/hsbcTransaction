package com.hsbc.transaction.interfaces.transaction.api;

import com.hsbc.transaction.application.transaction.service.TransactionAppService;
import com.hsbc.transaction.interfaces.transaction.converter.CreateTransactionRequestToEntityConverter;
import com.hsbc.transaction.interfaces.transaction.converter.ModifyTransactionRequestToEntityConverter;
import com.hsbc.transaction.interfaces.transaction.converter.TransactionQueryDTOToQueryConverter;
import com.hsbc.transaction.interfaces.transaction.converter.TransactionToDTOConverter;
import com.hsbc.transaction.interfaces.transaction.dto.CreateTransactionRequest;
import com.hsbc.transaction.interfaces.transaction.dto.ModifyTransactionRequest;
import com.hsbc.transaction.interfaces.transaction.dto.TransactionDTO;
import com.hsbc.transaction.interfaces.transaction.dto.TransactionQueryDTO;
import com.hsbc.transaction.util.annotation.Idempotent;
import com.hsbc.transaction.util.response.MyPage;
import com.hsbc.transaction.util.response.MyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * TransactionUIAPI
 *
 * @author Lei
 * @date 2025/6/18 19:44
 */

@RestController
@RequestMapping("/uiapi")
public class TransactionUIAPI {

    @Autowired
    private TransactionAppService transactionAppService;

    @PostMapping("/v1/transactions")
    @Idempotent
    public MyResult<TransactionDTO> createTransaction(@RequestBody CreateTransactionRequest createTransactionRequest) {
        return MyResult.success(TransactionToDTOConverter.INSTANCE.convert(transactionAppService.createTransaction(CreateTransactionRequestToEntityConverter
                .INSTANCE.converter(createTransactionRequest))));
    }

    @GetMapping("/v1/transactions")
    public MyResult<MyPage<TransactionDTO>> getTransactionsByQuery(TransactionQueryDTO transactionQueryDTO) {
        return MyResult.success(transactionAppService.getTransactionsByQuery(TransactionQueryDTOToQueryConverter.INSTANCE.convert(transactionQueryDTO))
                .convert(TransactionToDTOConverter.INSTANCE::convert));
    }

    @DeleteMapping("/v1/transactions/{transactionId}")
    public MyResult<Void> removeTransaction(@PathVariable Long transactionId) {
        transactionAppService.removeTransaction(transactionId);
        return MyResult.success();
    }

    @PostMapping("/v1/transactions")
    public MyResult<TransactionDTO> modifyTransaction(@RequestBody ModifyTransactionRequest modifyTransactionRequest) {
        return MyResult.success(TransactionToDTOConverter.INSTANCE.convert(transactionAppService
                .modifyTransaction(ModifyTransactionRequestToEntityConverter.INSTANCE.convert(modifyTransactionRequest))));
    }

}
