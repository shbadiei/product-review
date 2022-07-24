package com.codechallenge.product.uaa.exception;

import com.codechallenge.product.common.BaseException;

public class UAAException extends BaseException {

    public UAAException(UAAErrorInfo errorMessage) {
        super(errorMessage);
    }

    public UAAException(UAAErrorInfo errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}
