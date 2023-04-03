package com.gist.graduation.auth.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class GoogleAuthRequest {

    private String name;
    private String email;
    private String idToken;

    public GoogleAuthRequest(String name, String email, String idToken) {
        this.name = name;
        this.email = email;
        this.idToken = idToken;
    }
}
