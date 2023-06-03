package com.gist.graduation.user.presentation;

import com.gist.graduation.auth.annotation.AuthenticationPrincipal;
import com.gist.graduation.auth.annotation.GoogleIdTokenRequired;
import com.gist.graduation.requirment.domain.GraduationRequirementStatus;
import com.gist.graduation.requirment.domain.major.MajorType;
import com.gist.graduation.user.application.UserService;
import com.gist.graduation.user.domain.User;
import com.gist.graduation.user.dto.UserTakenCoursesAndGradeResponse;
import com.gist.graduation.user.dto.UserTakenCoursesRequest;
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

    @PutMapping("/taken-courses")
    @GoogleIdTokenRequired
    public ResponseEntity<?> changeTakenCourses(@AuthenticationPrincipal User user, @Valid @RequestBody UserTakenCoursesRequest userTakenCoursesRequest) {
        userService.updateTakenCourses(user, userTakenCoursesRequest);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/major")
    @GoogleIdTokenRequired
    public ResponseEntity<?> changeMajor(@AuthenticationPrincipal User user, @RequestBody MajorType majorType) {
        userService.updateMajor(user, majorType);
        return ResponseEntity.noContent().build();
    }
}
