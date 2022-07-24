package com.codechallenge.product.common;

import com.codechallenge.product.uaa.exception.UAAErrorInfo;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

    private final UAAErrorInfo errorMessage;

    public UAAErrorInfo getErrorMessage() {
        return errorMessage;
    }

    public BaseException(UAAErrorInfo errorMessage) {
        super(errorMessage.getMsg());
        this.errorMessage = errorMessage;
    }

    public BaseException(UAAErrorInfo errorMessage, Throwable cause) {
        super(errorMessage.getMsg(), cause);
        this.errorMessage = errorMessage;
    }
}
