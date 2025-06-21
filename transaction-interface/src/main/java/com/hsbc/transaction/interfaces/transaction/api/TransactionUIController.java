package com.hsbc.transaction.interfaces.transaction.api;

import com.hsbc.transaction.application.transaction.service.TransactionAppService;
import com.hsbc.transaction.domian.transaction.query.TransactionQuery;
import com.hsbc.transaction.interfaces.transaction.converter.CreateTransactionRequestToEntityConverter;
import com.hsbc.transaction.interfaces.transaction.converter.ModifyTransactionRequestToEntityConverter;
import com.hsbc.transaction.interfaces.transaction.converter.TransactionQueryDTOToQueryConverter;
import com.hsbc.transaction.interfaces.transaction.converter.TransactionToDTOConverter;
import com.hsbc.transaction.interfaces.transaction.dto.CreateTransactionRequest;
import com.hsbc.transaction.interfaces.transaction.dto.ModifyTransactionRequest;
import com.hsbc.transaction.interfaces.transaction.dto.TransactionDTO;
import com.hsbc.transaction.interfaces.transaction.dto.TransactionQueryDTO;
import com.hsbc.transaction.util.context.AppContext;
import com.hsbc.transaction.util.response.MyPage;
import com.hsbc.transaction.util.response.MyResult;
import jakarta.validation.Valid;
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
public class TransactionUIController {

    @Autowired
    private TransactionAppService transactionAppService;

    /**
     * 创建交易
     *
     * @param createTransactionRequest 创建交易请求入参
     * @return 创建的交易
     */
    @PostMapping("/v1/transactions")
    public MyResult<TransactionDTO> createTransaction(@Valid @RequestBody CreateTransactionRequest createTransactionRequest) {
        return MyResult.success(TransactionToDTOConverter.INSTANCE.convert(transactionAppService.createTransaction(String.valueOf(AppContext.getAccountId()),
                CreateTransactionRequestToEntityConverter.INSTANCE.convert(createTransactionRequest))));
    }

    /**
     * 分页查询交易列表
     *
     * @param transactionQueryDTO 查询入参
     * @return 交易列表
     */
    @GetMapping("/v1/transactions")
    public MyResult<MyPage<TransactionDTO>> getTransactionsByQuery(TransactionQueryDTO transactionQueryDTO) {
        TransactionQuery query = TransactionQueryDTOToQueryConverter.INSTANCE.convert(transactionQueryDTO);
        query.initPageParam();
        return MyResult.success(transactionAppService.getTransactionsByQuery(query)
                .convert(TransactionToDTOConverter.INSTANCE::convert));
    }

    /**
     * 删除交易
     *
     * @param transactionId 交易id
     * @return 删除结果
     */
    @DeleteMapping("/v1/transactions/{transactionId}")
    public MyResult<Void> removeTransaction(@PathVariable Long transactionId) {
        transactionAppService.removeTransaction(String.valueOf(AppContext.getAccountId()), transactionId);
        return MyResult.success();
    }

    /**
     * 更新交易
     *
     * @param modifyTransactionRequest 更新交易的入参
     * @return 更新后的交易
     */
    @PutMapping("/v1/transactions")
    public MyResult<TransactionDTO> modifyTransaction(@Valid @RequestBody ModifyTransactionRequest modifyTransactionRequest) {
        return MyResult.success(TransactionToDTOConverter.INSTANCE.convert(transactionAppService
                .modifyTransaction(String.valueOf(AppContext.getAccountId()),
                        ModifyTransactionRequestToEntityConverter.INSTANCE.convert(modifyTransactionRequest))));
    }

}
