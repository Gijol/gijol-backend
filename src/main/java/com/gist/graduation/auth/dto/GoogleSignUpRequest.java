package com.gist.graduation.auth.dto;

import com.gist.graduation.user.domain.UserTakenCourse;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class GoogleSignUpRequest extends GoogleAuthRequest {

    private String studentId;
    private List<UserTakenCourse> userTakenCourseList;

    public GoogleSignUpRequest(String name, String email, String idToken, String studentId, List<UserTakenCourse> userTakenCourseList) {
        this.name = name;
        this.email = email;
        this.idToken = idToken;
        this.studentId = studentId;
        this.userTakenCourseList = userTakenCourseList;
    }
}
