
package com.gist.graduation.auth.argumentresolver;

import com.gist.graduation.auth.annotation.AuthenticationPrincipal;
import com.gist.graduation.auth.application.GoogleAuthService;
import com.gist.graduation.auth.application.jwt.JWTAuthorizationHeaderParser;
import com.gist.graduation.auth.dto.GoogleIdTokenVerificationResponse;
import com.gist.graduation.auth.infra.GoogleAuthTokenVerifier;
import com.gist.graduation.config.exception.ApplicationException;
import com.gist.graduation.config.exception.AuthorizationException;
import com.gist.graduation.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

@Component
@RequiredArgsConstructor
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private final GoogleAuthTokenVerifier googleAuthTokenVerifier;
    private final GoogleAuthService googleAuthService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AuthenticationPrincipal.class)
                && parameter.getParameterType().equals(User.class);
    }

    @Override
    public User resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        final HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        final String token = request.getHeader(AUTHORIZATION_HEADER);
        if (token.isBlank()) {
            throw new ApplicationException("No token in Header");
        }
        final String idToken = JWTAuthorizationHeaderParser.parse(token);

        final GoogleIdTokenVerificationResponse response = googleAuthTokenVerifier.verifyGoogleOAuth2IdToken(idToken);

        return googleAuthService.findUserFromVerifiedIdTokenResponse(response)
                .orElseThrow(AuthorizationException::unAuthorized);
    }
}
