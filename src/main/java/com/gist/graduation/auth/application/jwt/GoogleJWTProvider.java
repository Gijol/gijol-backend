package com.gist.graduation.auth.application.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Verification;
import org.springframework.stereotype.Component;

@Component
public class GoogleJWTProvider extends JWTProvider{

    @Override
    void verifyJwt(String token) {
    }

    @Override
    void decodeByType(String token) {

    }
}
