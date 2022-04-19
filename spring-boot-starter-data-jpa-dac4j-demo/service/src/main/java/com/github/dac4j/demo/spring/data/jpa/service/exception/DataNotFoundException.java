package com.github.dac4j.demo.spring.data.jpa.service.exception;

import com.github.dac4j.demo.spring.data.jpa.service.exception.common.ApplicationException;

public class DataNotFoundException extends ApplicationException {

    public DataNotFoundException() {
        super(404);
    }

    public DataNotFoundException(String message) {
        super(404, message);
    }


}
