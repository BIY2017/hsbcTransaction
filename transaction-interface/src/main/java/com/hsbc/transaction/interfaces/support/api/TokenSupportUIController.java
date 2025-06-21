package com.hsbc.transaction.interfaces.support.api;

import com.hsbc.transaction.application.support.TokenSupportAppService;
import com.hsbc.transaction.util.response.MyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TokenSupportUIAPI
 *
 * @author Lei
 * @date 2025/6/19 20:59
 */
@RestController
@RequestMapping("/uiapi")
public class TokenSupportUIController {

    @Autowired
    private TokenSupportAppService tokenSupportAppService;

    @GetMapping("/v1/idemp-token")
    public MyResult<String> generateToken() {
        return MyResult.success(tokenSupportAppService.generateIdempotent());
    }
}
