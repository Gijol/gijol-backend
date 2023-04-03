package com.gist.graduation.auth.application.jwt;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

@Component
public class GoogleJWTProvider extends JWTProvider{


    @Override
    public void verifyJwt(String token) {
        DecodedJWT decodedJWT = this.decodeJWT(token);
        verifyGoogleOAuthIssur(decodedJWT);
        verifyEmailVerified(decodedJWT);
    }

    private void verifyGoogleOAuthIssur(DecodedJWT decodedJWT) {
        String issuer = decodedJWT.getIssuer();
        if(!issuer.contains("https://accounts.google.com")){
            throw new IllegalArgumentException("유효하지 않은 토큰입니다.");
        }
    }

    private void verifyEmailVerified(DecodedJWT decodedJWT){
        if(!decodedJWT.getClaim("email_verified").asBoolean()){
            throw new IllegalArgumentException("이메일 인증이 되지 않은 사용자입니다.");
        }
    }

    @Override
    public LoginUser decodeToLoginUserByType(String token) {
        DecodedJWT decodedJWT = this.decodeJWT(token);
        String email = decodedJWT.getClaim("email").asString();
        String name = decodedJWT.getClaim("name").asString();
        return new LoginUser(email, name);
    }
}
