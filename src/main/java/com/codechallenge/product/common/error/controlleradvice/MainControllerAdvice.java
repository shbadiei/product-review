package com.codechallenge.product.common.error.controlleradvice;

import com.codechallenge.product.common.error.BaseErrorInfo;
import com.codechallenge.product.common.error.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class MainControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<Object> handleCityNotFoundException(BaseException ex, WebRequest request) {

        BaseErrorInfo errorInfo = ex.getErrorInfo();
        if (errorInfo != null) {
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("timestamp", new Date());
            body.put("errorCode", errorInfo.getErrorCode());
            body.put("errorMsg", errorInfo.getMsg());
            body.put("exceptionClazz", ex.getClass().getSimpleName());
            return new ResponseEntity<>(body, errorInfo.getHttpStatus());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }


}
