package com.hsbc.transaction.util.exception;

import com.hsbc.transaction.util.response.MyResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * ExceptionHandler
 *
 * @author Lei
 * @date 2025/6/19 12:53
 */

@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(value = MyException.class)
    public MyResult<Void> process(MyException e) {
        return MyResult.error(e.getErrorCode());
    }

    @ExceptionHandler(value = Exception.class)
    public MyResult<Void> process(Exception e) {
        return MyResult.error(ErrorCode.SYSTEM_ERROR);
    }
}
