package com.gist.graduation.auth.infra;

import com.gist.graduation.auth.dto.GoogleAuthBaseResponse;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface OAuthTokenVerifier {

    GoogleAuthBaseResponse verify(String token) throws NoSuchAlgorithmException, InvalidKeySpecException;
}
