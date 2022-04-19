package com.github.dac4j.demo.spring.data.jpa.api.exception;

import com.github.dac4j.demo.spring.data.jpa.service.exception.DataNotFoundException;
import com.github.dac4j.demo.spring.data.jpa.service.exception.common.ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Optional;

@ControllerAdvice
@Slf4j
public class ExceptionAdvice {

    private static final String INTERNAL_SERVER_ERROR = "Internal Server Error";

    @ExceptionHandler(value = {DataNotFoundException.class})
    public ResponseEntity<ExceptionResponse> handleDataAccessControlEntityNotFound(DataNotFoundException e) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .code(HttpStatus.NOT_FOUND.value())
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {ApplicationException.class})
    public ResponseEntity<ExceptionResponse> handleApplicationException(ApplicationException e) {
        if(log.isDebugEnabled()) log.error(e.getMessage(), e);
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .code(e.getCode())
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(exceptionResponse,
                Optional.ofNullable(HttpStatus.resolve(e.getCode())).orElse(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ExceptionResponse> handleDefault(Exception e) {
        if(log.isDebugEnabled()) log.error(e.getMessage(), e);
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(INTERNAL_SERVER_ERROR)
                .build();

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
