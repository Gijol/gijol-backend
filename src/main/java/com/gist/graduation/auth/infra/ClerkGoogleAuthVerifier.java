package com.gist.graduation.auth.infra;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.gist.graduation.auth.dto.GoogleAuthBaseResponse;
import com.gist.graduation.config.exception.AuthorizationException;
import com.gist.graduation.config.exception.InternalApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;

@Component
@Slf4j
public class ClerkGoogleAuthVerifier implements OAuthTokenVerifier {

    private final String publicKeyString;
    private final String[] allowedUrls;
    private final Long accessTokenTime;
    private static final String EMAIL = "email";


    public ClerkGoogleAuthVerifier(
            @Value("${clerk.public-key}") String publicKeyString,
            @Value("${clerk.access-token.timeout:900}") Long accessTokenTime,
            @Value("${request.origins:http://localhost:3000}") String[] allowedUrls
    ) {
        this.publicKeyString = publicKeyString;
        this.accessTokenTime = accessTokenTime;
        this.allowedUrls = allowedUrls;
    }

    @Override
    public GoogleAuthBaseResponse verify(String token) {
        try {
            final JWTVerifier jwtVerifier = JWT.require(Algorithm.RSA256(getRSAPublicKey(), null))
                    .acceptExpiresAt(accessTokenTime)
                    .withClaim("azp", (claim, decodedJWT) -> Arrays.stream(allowedUrls)
                            .anyMatch(url -> url.equals(claim.asString()))
                    )
                    .build();
            final DecodedJWT decodedJWT = jwtVerifier.verify(token);
            validateDecodedJwt(decodedJWT);
            return new GoogleAuthBaseResponse(decodedJWT.getClaim(EMAIL).asString());
        } catch (JWTVerificationException e) {
            throw new AuthorizationException("유효하지 않은 토큰입니다. " + e.getMessage());
        } catch (Exception e) {
            throw new InternalApplicationException(e.getMessage());
        }
    }

    private void validateDecodedJwt(DecodedJWT decodedJWT) {
        if (!decodedJWT.getClaims().containsKey(EMAIL)) {
            throw new AuthorizationException("토큰에 이메일이 존재하지 않습니다.");
        }
    }

    private RSAPublicKey getRSAPublicKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] decode = Base64.getDecoder().decode(this.publicKeyString);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return (RSAPublicKey) kf.generatePublic(new X509EncodedKeySpec(decode));
    }
}
