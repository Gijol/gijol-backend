package com.gist.graduation.auth.application.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

public abstract class JWTProvider {

    @Value("${jwt.secretKey:secret_temp}")
    private String secretKey ;
    protected final long accessTokenDurationTime = 10 * 60 * 1000;

    protected final Algorithm algorithm = Algorithm.HMAC256(secretKey);
    protected final Verification verification = JWT.require(this.algorithm);


    protected String createJWT(String email, long tokenDurationTime) {
        Date now = new Date();
        Date expirationTime = new Date(now.getTime() + tokenDurationTime);

        return JWT.create()
                .withSubject(email)
                .withIssuedAt(now)
                .withExpiresAt(expirationTime)
                .sign(algorithm);
    }
    protected DecodedJWT decodeJWT(String token) {
        return JWT.decode(token);
    }

    abstract void verifyJwt(String token);

    abstract void decodeByType(String token);


}
