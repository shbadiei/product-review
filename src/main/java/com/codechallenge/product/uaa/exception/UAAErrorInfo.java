package com.codechallenge.product.uaa.exception;

import com.codechallenge.product.common.error.BaseErrorInfo;
import org.springframework.http.HttpStatus;

public enum UAAErrorInfo implements BaseErrorInfo {

    InvalidJWTToken(HttpStatus.UNAUTHORIZED)
    ;

    private String msg;

    private HttpStatus httpStatus;

    UAAErrorInfo(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        this.msg = "";
    }

    UAAErrorInfo(HttpStatus httpStatus, String msg) {
        this.httpStatus = httpStatus;
        this.msg = msg;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
