package com.hsbc.transaction.util.exception;

import com.hsbc.transaction.util.response.MyResult;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

/**
 * ExceptionHandler
 *
 * @author Lei
 * @date 2025/6/19 12:53
 */

@RestControllerAdvice
@Slf4j
public class MyExceptionHandler {

    @ExceptionHandler(value = BindException.class)
    public MyResult<Void> process(BindException e) {
        log.error("Validation Error!", e);
        return MyResult.error(ErrorCode.ILLEGAL_REQUEST.getCode(), e.getBindingResult()
                .getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(",")));
    }

    @ExceptionHandler(value = MyException.class)
    public MyResult<Void> process(MyException e) {
        log.error("Custom Error!", e);
        return MyResult.error(e.getErrorCode());
    }

    @ExceptionHandler(value = Exception.class)
    public MyResult<Void> process(Exception e) {
        log.error("System Error!", e);
        return MyResult.error(ErrorCode.SYSTEM_ERROR);
    }
}
