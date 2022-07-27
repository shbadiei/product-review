package com.codechallenge.product.uaa.exception;

import com.codechallenge.product.common.error.BaseErrorInfo;
import org.springframework.http.HttpStatus;

public enum UAAErrorInfo implements BaseErrorInfo {

    //Module Error Index 4

    InvalidJWTToken(HttpStatus.UNAUTHORIZED,401401L),
    BadCredentials(HttpStatus.BAD_REQUEST,400402L);

    private String msg;

    private HttpStatus httpStatus;

    private Long errorCode;

    UAAErrorInfo(HttpStatus httpStatus, Long errorCode) {
        this.httpStatus = httpStatus;
        this.msg = this.name();
        this.errorCode = errorCode;
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

    @Override
    public Long getErrorCode() {
        return errorCode;
    }
}
