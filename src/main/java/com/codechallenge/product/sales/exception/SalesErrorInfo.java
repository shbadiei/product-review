package com.codechallenge.product.sales.exception;

import com.codechallenge.product.common.error.BaseErrorInfo;
import org.springframework.http.HttpStatus;

public enum SalesErrorInfo implements BaseErrorInfo {
    AlreadyVoteForThisProductSaleInfo(HttpStatus.BAD_REQUEST);

    private String msg;

    private HttpStatus httpStatus;

    SalesErrorInfo(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        this.msg = "";
    }

    @Override
    public String getMsg() {
        return null;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return null;
    }
}
