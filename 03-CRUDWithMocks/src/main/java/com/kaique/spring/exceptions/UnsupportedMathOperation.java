package com.kaique.spring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnsupportedMathOperation extends RuntimeException {

    private static final Long serialVersionUID = 1L;

    public UnsupportedMathOperation() {
        super("Please set a numerical value.");
    }

    public UnsupportedMathOperation(String ex) {
        super(ex);
    }
}