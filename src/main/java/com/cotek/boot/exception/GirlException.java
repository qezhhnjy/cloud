package com.cotek.boot.exception;

import lombok.Data;

/**
 * @author qezhhnjy
 * @date 2018/7/2-1:12
 */

@Data
public class GirlException extends RuntimeException {

    private int code;

    private String message;
}
