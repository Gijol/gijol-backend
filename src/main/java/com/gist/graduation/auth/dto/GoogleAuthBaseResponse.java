package com.gist.graduation.auth.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class GoogleAuthBaseResponse {
    private String email;

    public GoogleAuthBaseResponse(String email) {
        this.email = email;
    }
}
