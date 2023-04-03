package com.gist.graduation.auth.application.jwt;

import com.gist.graduation.config.exception.ApplicationException;
import org.springframework.stereotype.Component;

@Component
public class JWTAuthorizationHeaderParser {

    private static final String BEARER = "Bearer ";

    public static String parse(String authorizationHeader) {
        String trimmedToken = authorizationHeader.trim();
        if(!trimmedToken.startsWith(BEARER)){
            throw new ApplicationException("유효한 토큰 형식이 아닙니다.");
        }

        return authorizationHeader.substring(BEARER.length());
    }

}
