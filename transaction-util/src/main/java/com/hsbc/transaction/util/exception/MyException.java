package com.hsbc.transaction.util.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * MyException
 *
 * @author Lei
 * @date 2025/6/19 12:59
 */

@Getter
@Setter
@NoArgsConstructor
public class MyException extends RuntimeException {

    private ErrorCode errorCode;

    public MyException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
