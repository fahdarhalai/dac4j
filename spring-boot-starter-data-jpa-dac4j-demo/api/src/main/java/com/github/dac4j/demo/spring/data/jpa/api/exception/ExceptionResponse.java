package com.github.dac4j.demo.spring.data.jpa.api.exception;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ExceptionResponse {
    private int code;
    private String message;
}
