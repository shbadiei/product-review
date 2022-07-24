package com.codechallenge.product.common;

import org.springframework.http.HttpStatus;

public interface BaseErrorInfo {

    String getMsg();

    HttpStatus getHttpStatus();

}
