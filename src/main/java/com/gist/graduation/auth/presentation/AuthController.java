package com.gist.graduation.auth.presentation;

import com.gist.graduation.auth.dto.GoogleAuthRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AuthController {


    @PostMapping("/auth/google")
    public ResponseEntity<?> singUpGoogleAuth(@RequestBody GoogleAuthRequest request){

        // find user by email in db

        return ResponseEntity.ok(null);
    }
}
