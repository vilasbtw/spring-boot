package com.kaique.spring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequiredObjectIsNullException extends RuntimeException {

    private static final Long serialVersionUID = 1L;

    public RequiredObjectIsNullException() {
        super("A null object is not allowed to persist.");
    }

    public RequiredObjectIsNullException(String ex) {
        super(ex);
    }
}
