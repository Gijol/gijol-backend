package com.gist.graduation.user.presentation;

import com.gist.graduation.auth.annotation.AuthenticationPrincipal;
import com.gist.graduation.auth.annotation.GoogleIdTokenRequired;
import com.gist.graduation.requirment.domain.GraduationRequirementStatus;
import com.gist.graduation.user.application.UserService;
import com.gist.graduation.user.domain.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users/me")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/graduation")
    @GoogleIdTokenRequired
    public ResponseEntity<?> checkGraduationRequirementForLoginUser(@AuthenticationPrincipal User user){
        GraduationRequirementStatus graduationRequirementStatus = userService.checkGraduationRequirementForUser(user);
        return ResponseEntity.ok(graduationRequirementStatus);
    }
}
