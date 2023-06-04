package com.gist.graduation.config.exception;

import org.springframework.http.HttpStatus;

public class InternalApplicationException extends ApplicationException{

    public InternalApplicationException(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
