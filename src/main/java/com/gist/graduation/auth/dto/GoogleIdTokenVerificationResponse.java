package com.gist.graduation.auth.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class GoogleIdTokenVerificationResponse {

    private String iss;
    private String azp;
    private String aud;
    private String email;
    private Boolean email_verified;
    private String at_hash;
    private String name;
    private String picture;
    private String given_name;
    private String family_name;
    private String locale;
    private String iat;
    private String exp;
    private String alg;
    private String kid;
    private String typ;

    public GoogleIdTokenVerificationResponse(String iss, String azp, String aud, String email, Boolean email_verified, String at_hash, String name, String picture, String given_name, String family_name, String locale, String iat, String exp, String alg, String kid, String typ) {
        this.iss = iss.trim();
        this.azp = azp.trim();
        this.aud = aud.trim();
        this.email = email.trim();
        this.email_verified = email_verified;
        this.at_hash = at_hash.trim();
        this.name = name.trim();
        this.picture = picture.trim();
        this.given_name = given_name.trim();
        this.family_name = family_name.trim();
        this.locale = locale.trim();
        this.iat = iat.trim();
        this.exp = exp.trim();
        this.alg = alg.trim();
        this.kid = kid.trim();
        this.typ = typ.trim();
    }

    public boolean isNotValid() {
        return !email_verified;
    }

}
