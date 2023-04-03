package com.gist.graduation.auth.presentation;

import com.gist.graduation.auth.application.GoogleAuthService;
import com.gist.graduation.auth.application.AuthType;
import com.gist.graduation.auth.dto.GoogleAuthRequest;
import com.gist.graduation.auth.dto.GoogleSignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AuthController {

    private final GoogleAuthService googleAuthService;

    @PostMapping("/auth/google")
    public ResponseEntity<?> singUpGoogleAuth(@RequestBody GoogleAuthRequest request){

        AuthType googleLoginType = googleAuthService.findGoogleLoginType(request);

        return ResponseEntity.ok(googleLoginType);
    }

    @PostMapping("/auth/google/sign-in")
    public ResponseEntity<?> signInGoogleAuth(@RequestBody GoogleAuthRequest request){
        AuthType googleLoginType = googleAuthService.findGoogleLoginType(request);

        return ResponseEntity.ok(googleLoginType);
    }

    @PostMapping("/auth/google/sign-up")
    public ResponseEntity<?> signUpGoogleAuth(@RequestBody GoogleSignUpRequest request){
        Long id = googleAuthService.signUp(request);
        return ResponseEntity.created(URI.create(id.toString())).build();
    }
}
