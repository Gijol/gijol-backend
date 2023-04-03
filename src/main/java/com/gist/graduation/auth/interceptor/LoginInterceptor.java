package com.gist.graduation.auth.interceptor;

import com.gist.graduation.auth.annotation.LoginRequired;
import com.gist.graduation.config.exception.ApplicationException;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (handler instanceof HandlerMethod && ((HandlerMethod) handler).hasMethodAnnotation(LoginRequired.class)) {
            String credentials = request.getHeader(AUTHORIZATION_HEADER);
            if (Objects.isNull(credentials) || credentials.isBlank()) {
                throw new ApplicationException("존재하지 않는 토큰입니다.");
            }
        }
        return true;
    }

}
