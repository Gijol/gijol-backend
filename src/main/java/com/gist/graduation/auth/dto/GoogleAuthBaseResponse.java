package com.gist.graduation.auth.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class GoogleAuthBaseResponse {
    private String email;
    private String name;

    public GoogleAuthBaseResponse(String email, String name) {
        this.email = email;
        this.name = name;
    }

}
