package com.codechallenge.product.sales.exception;

import com.codechallenge.product.common.error.BaseException;
import com.codechallenge.product.uaa.exception.UAAErrorInfo;

public class SalesException extends BaseException {
    public SalesException(SalesErrorInfo errorMessage) {
        super(errorMessage);
    }
}
