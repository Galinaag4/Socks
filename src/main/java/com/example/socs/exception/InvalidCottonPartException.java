package com.example.socs.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InvalidCottonPartException extends RuntimeException {
    public InvalidCottonPartException(String message) {
        super(message);
    }
}
