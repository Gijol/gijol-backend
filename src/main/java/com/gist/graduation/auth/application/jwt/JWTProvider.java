package com.gist.graduation.auth.application.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;

import java.util.Date;

public abstract class JWTProvider {

    private String secretKey = "secret_temp";
    protected final long accessTokenDurationTime = 10L * 60L * 1000L;

    protected final Algorithm algorithm = Algorithm.HMAC256(secretKey);
    protected final Verification verification = JWT.require(this.algorithm);


    public String createJWT(String email, long tokenDurationTime) {
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

    public abstract void verifyJwt(String token);

    public abstract LoginUser decodeToLoginUserByType(String token);


}
