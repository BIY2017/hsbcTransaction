package com.hsbc.transaction.util.response;

import com.hsbc.transaction.util.exception.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * MyResult
 *
 * @author Lei
 * @date 2025/6/18 19:52
 */

@Getter
@Setter
@NoArgsConstructor
public class MyResult<T> {

    private String code;

    private String message;

    private T data;

    public boolean isSuccess() {
        return ErrorCode.SUCCESS.getCode().equals(code);
    }

    public static <T> MyResult<T> success(T data) {
        return new MyResult<>(ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getMessage(), data);
    }

    public static MyResult<Void> success() {
        return new MyResult<>(ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getMessage(), null);
    }

    public static MyResult<Void> error(ErrorCode errorCode) {
        return new MyResult<>(errorCode.getCode(), errorCode.getMessage());
    }


    private MyResult(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private MyResult(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
