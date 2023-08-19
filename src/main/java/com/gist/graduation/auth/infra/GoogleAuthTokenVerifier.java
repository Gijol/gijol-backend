package com.gist.graduation.auth.infra;

import com.gist.graduation.auth.dto.GoogleIdTokenVerificationResponse;
import com.gist.graduation.config.exception.AuthorizationException;
import com.gist.graduation.config.exception.InternalApplicationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
@RequiredArgsConstructor
public class GoogleAuthTokenVerifier {

    private final RestTemplate restTemplate;

    public GoogleIdTokenVerificationResponse verifyGoogleOAuth2IdToken(String idToken) {
        String url = "https://oauth2.googleapis.com/tokeninfo?id_token=" + idToken;
        GoogleIdTokenVerificationResponse response;
        try {
            response = restTemplate.getForObject(url, GoogleIdTokenVerificationResponse.class);
        } catch (Exception e) {
            throwIfInvalidToken(e);
            throw new InternalApplicationException(e.getMessage());
        }

        if (response == null || response.isNotValid())
            throw new AuthorizationException("유효하지 않은 Google OAuth 토큰입니다.");
        return response;
    }

    private static void throwIfInvalidToken(Exception e) {
        if (e.getMessage().contains("Invalid Value")) {
            throw new AuthorizationException("유효하지 않은 Google OAuth 토큰입니다.");
        }
    }

}
