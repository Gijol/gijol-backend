package com.gist.graduation.user.presentation;

import com.gist.graduation.auth.annotation.AuthenticationPrincipal;
import com.gist.graduation.auth.annotation.GoogleIdTokenRequired;
import com.gist.graduation.requirment.domain.GraduationRequirementStatus;
import com.gist.graduation.user.application.UserService;
import com.gist.graduation.user.domain.User;
import com.gist.graduation.user.dto.*;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users/me")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/graduation")
    @GoogleIdTokenRequired
    public ResponseEntity<?> checkGraduationRequirementForLoginUser(@AuthenticationPrincipal User user) {
        GraduationRequirementStatus graduationRequirementStatus = userService.checkGraduationRequirementForUser(user);
        return ResponseEntity.ok(graduationRequirementStatus);
    }

    @GetMapping("/taken-courses")
    @GoogleIdTokenRequired
    public ResponseEntity<?> checkTakenCourse(@AuthenticationPrincipal User user) {
        UserTakenCoursesAndGradeResponse userTakenCourseAndAverageGrade = userService.findUserTakenCourseAndAverageGrade(user);
        return ResponseEntity.ok(userTakenCourseAndAverageGrade);
    }

    @GetMapping("")
    @GoogleIdTokenRequired
    public ResponseEntity<UserInfoResponse> getUserInfo(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(userService.getUserInfo(user));
    }

    @PutMapping("/taken-courses")
    @GoogleIdTokenRequired
    public ResponseEntity<?> changeTakenCourses(@AuthenticationPrincipal User user, @Valid @RequestBody UserTakenCoursesRequest userTakenCoursesRequest) {
        userService.updateTakenCourses(user, userTakenCoursesRequest);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/major")
    @GoogleIdTokenRequired
    public ResponseEntity<?> changeMajor(@AuthenticationPrincipal User user, @RequestBody UpdateMajorRequest request) {
        userService.updateMajor(user, request.getMajorType());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/name")
    @GoogleIdTokenRequired
    public ResponseEntity<?> changeName(@AuthenticationPrincipal User user, @RequestBody UpdateNameRequest request) {
        userService.updateName(user, request.getName());
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "회원 탈퇴 API", notes = "헤더에서 Oauth 토큰과 함께 요청해야 합니다")
    @DeleteMapping("")
    @GoogleIdTokenRequired
    public ResponseEntity<?> signOut(@AuthenticationPrincipal User user) {
        userService.signOut(user.getId());
        return ResponseEntity.noContent().build();
    }
}
