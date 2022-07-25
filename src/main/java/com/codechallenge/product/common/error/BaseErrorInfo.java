package com.codechallenge.product.common.error;

import org.springframework.http.HttpStatus;

public interface BaseErrorInfo {

    String getMsg();

    HttpStatus getHttpStatus();

}
