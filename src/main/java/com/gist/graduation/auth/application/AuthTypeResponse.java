package com.gist.graduation.auth.application;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class AuthTypeResponse {

    @JsonProperty("isNewUser")
    private boolean isNewUser;

    @JsonIgnore
    public boolean isNewUser() {
        return isNewUser;
    }
}
