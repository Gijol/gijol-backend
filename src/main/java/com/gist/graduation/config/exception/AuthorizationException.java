package com.gist.graduation.config.exception;

import org.springframework.http.HttpStatus;

public class AuthorizationException extends ApplicationException{

    public AuthorizationException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }

    public static AuthorizationException unAuthorized() {
        return new AuthorizationException("회원가입하지 않은 유저입니다.");
    }
}
