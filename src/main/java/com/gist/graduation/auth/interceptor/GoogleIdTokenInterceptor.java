package com.gist.graduation.auth.interceptor;

import com.gist.graduation.auth.annotation.GoogleIdTokenRequired;
import com.gist.graduation.auth.application.jwt.JWTAuthorizationHeaderParser;
import com.gist.graduation.config.exception.AuthorizationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class GoogleIdTokenInterceptor implements HandlerInterceptor {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String credentials = request.getHeader(AUTHORIZATION_HEADER);
        if (handler instanceof HandlerMethod && ((HandlerMethod) handler).hasMethodAnnotation(GoogleIdTokenRequired.class)) {
            if (Objects.isNull(credentials) || credentials.isBlank()) {
                throw new AuthorizationException("유효한 토큰이 아닙니다.");
            }
            String token = JWTAuthorizationHeaderParser.parse(credentials);
            request.setAttribute("token", token);
        }

        return true;
    }

}
