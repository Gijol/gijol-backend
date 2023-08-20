package com.gist.graduation.auth.infra;

import com.gist.graduation.auth.dto.GoogleAuthBaseResponse;

public interface GoogleOAuthTokenVerifier {

    GoogleAuthBaseResponse verify(String token);
}
