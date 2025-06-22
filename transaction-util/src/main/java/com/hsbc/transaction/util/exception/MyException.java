package com.hsbc.transaction.util.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyException that = (MyException) o;
        return errorCode == that.errorCode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(errorCode);
    }
}
