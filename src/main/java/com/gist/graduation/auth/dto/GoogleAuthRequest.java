package com.gist.graduation.auth.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class GoogleAuthRequest {

    private String name;
    private String email;
    private String expires;

    public GoogleAuthRequest(String name, String email, String expires) {
        this.name = name;
        this.email = email;
        this.expires = expires;
    }
}
