package com.gist.graduation.auth.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class GoogleVerificationResponse {

    private String azp;
    private String aud;
    private String sub;
    private String scope;
    private String exp;
    private String expires_in;
    private String email;
    private Boolean email_verified;
    private String access_type;

    public GoogleVerificationResponse(String azp, String aud, String sub, String scope, String exp, String expires_in, String email, Boolean email_verified, String access_type) {
        this.azp = azp;
        this.aud = aud;
        this.sub = sub;
        this.scope = scope;
        this.exp = exp;
        this.expires_in = expires_in;
        this.email = email;
        this.email_verified = email_verified;
        this.access_type = access_type;
    }

    public boolean isNotValid() {
        return !email_verified;
    }


}
