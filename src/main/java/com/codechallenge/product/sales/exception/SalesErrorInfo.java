package com.codechallenge.product.sales.exception;

import com.codechallenge.product.common.error.BaseErrorInfo;
import org.springframework.http.HttpStatus;

public enum SalesErrorInfo implements BaseErrorInfo {

    //Module Error Index 3

    AlreadyVoteForThisProductSaleInfo(HttpStatus.BAD_REQUEST, 400301L),
    SalesInfoNotFound(HttpStatus.NOT_FOUND, 404302L)
    ;

    private String msg;

    private HttpStatus httpStatus;

    private Long errorCode;

    SalesErrorInfo(HttpStatus httpStatus, Long errorCode) {
        this.httpStatus = httpStatus;
        this.msg = this.name();
        this.errorCode = errorCode;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public Long getErrorCode() {
        return errorCode;
    }
}
