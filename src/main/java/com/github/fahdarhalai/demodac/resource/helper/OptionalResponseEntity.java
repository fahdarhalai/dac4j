package com.github.fahdarhalai.demodac.resource.helper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class OptionalResponseEntity {

    private OptionalResponseEntity() {}

    public static <D> ResponseEntity<D> okOrNotFound(Optional<D> optional) {
        return optional.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));

    }
}
