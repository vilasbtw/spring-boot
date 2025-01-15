package com.kaique.spring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private static final Long serialVersionUID = 1L;

    public ResourceNotFoundException() {
        super("Resource not found.");
    }

    public ResourceNotFoundException(String ex) {
        super(ex);
    }
}
