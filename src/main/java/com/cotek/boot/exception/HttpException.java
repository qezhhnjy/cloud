package com.cotek.boot.exception;

import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author qezhhnjy
 * @date 2018/7/2-1:11
 */
@ControllerAdvice
public class HttpException {

    @ExceptionHandler(BindException.class)
    public void girl(BindException e) {
    }
}
