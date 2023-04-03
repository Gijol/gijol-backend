package com.gist.graduation.auth.application.jwt;

import com.gist.graduation.auth.dto.GoogleAuthRequest;
import com.gist.graduation.config.exception.ApplicationException;
import lombok.Getter;

@Getter
public class LoginUser {
    private final String email;
    private final String name;

    public LoginUser(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public void verifyFromRequest(GoogleAuthRequest request){
        if(!this.name.equals(request.getName()) || !this.email.equals(request.getEmail())){
            throw new ApplicationException("토큰 정보와 사용자의 정보가 일치하지 않습니다.");
        }
    }
}
