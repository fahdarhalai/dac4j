package com.github.dac4j.demo.spring.data.jpa.service.exception.common;

import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {

    private int code = 500;

    public ApplicationException() {
    }

    public ApplicationException(int code) {
        this.code = code;
    }

    public ApplicationException(int code, String message) {
        super(message);
        this.code = code;
    }

    public ApplicationException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public ApplicationException(Throwable cause) {
        super(cause);
    }

    public ApplicationException(int code, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
    }
}
