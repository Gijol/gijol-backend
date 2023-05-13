package com.gist.graduation.auth.presentation;

import com.gist.graduation.auth.annotation.GoogleIdTokenRequired;
import com.gist.graduation.auth.application.AuthType;
import com.gist.graduation.auth.application.GoogleAuthService;
import com.gist.graduation.auth.dto.GoogleSignUpRequest;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AuthController {

    private final GoogleAuthService googleAuthService;

    @ApiOperation(value = "회원 존재 여부 API", notes = "회원이면 SIGN_IN, 회원이 아니면 SIGN_UP을 반환합니다. 헤더에서 구글 id 토큰과 함께 요청해야 합니다.")
    @PostMapping("/auth/google")
    @GoogleIdTokenRequired
    public ResponseEntity<?> singUpGoogleAuth(@RequestAttribute String idToken) {
        AuthType googleLoginType = googleAuthService.findGoogleLoginType(idToken);
        return ResponseEntity.ok(googleLoginType);
    }

    @ApiOperation(value = "회원 가입 API", notes = "회원 가입을 진행하는 API입니다. 헤더에서 구글 id 토큰과 함께 요청해야 합니다.")
    @PostMapping("/auth/google/sign-up")
    @GoogleIdTokenRequired
    public ResponseEntity<?> signUpGoogleAuth(@RequestAttribute String idToken, @RequestBody GoogleSignUpRequest request) {
        Long id = googleAuthService.signUp(request, idToken);
        return ResponseEntity.created(URI.create(id.toString())).build();
    }
}
