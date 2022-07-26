package com.codechallenge.product.common.error;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

    private final BaseErrorInfo errorInfo;

    public BaseErrorInfo getErrorMessage() {
        return errorInfo;
    }

    public BaseException(BaseErrorInfo errorInfo) {
        super(errorInfo.getMsg());
        this.errorInfo = errorInfo;
    }

    public BaseException(BaseErrorInfo errorInfo, Throwable cause) {
        super(errorInfo.getMsg(), cause);
        this.errorInfo = errorInfo;
    }
}
