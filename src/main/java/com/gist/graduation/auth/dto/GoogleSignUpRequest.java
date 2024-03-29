package com.gist.graduation.auth.dto;

import com.gist.graduation.requirment.domain.major.MajorType;
import com.gist.graduation.user.domain.UserTakenCourse;
import com.gist.graduation.user.taken_course.CourseType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class GoogleSignUpRequest {

    @NotBlank
    private String name;
    @NotBlank
    private String studentId;
    private MajorType majorType;
    private List<UserTakenCourseRequest> userTakenCourseList;

    public GoogleSignUpRequest(String naem, String studentId, MajorType majorType, List<UserTakenCourseRequest> userTakenCourseList) {
        this.name = naem;
        this.studentId = studentId;
        this.majorType = majorType;
        this.userTakenCourseList = userTakenCourseList;
    }

    public List<UserTakenCourse> toUserTakenCourseEntityList() {
        return this.userTakenCourseList.stream()
                .map(UserTakenCourseRequest::toEntity)
                .collect(Collectors.toList());
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    static class UserTakenCourseRequest {
        private int year;
        private String semester;
        private CourseType courseType;
        private String courseName;
        private String courseCode;
        private Integer credit;
        private String grade;

        public UserTakenCourseRequest(int year, String semester, CourseType courseType, String courseName, String courseCode, Integer credit, String grade) {
            this.year = year;
            this.semester = semester;
            this.courseType = courseType;
            this.courseName = courseName;
            this.courseCode = courseCode;
            this.credit = credit;
            this.grade = grade;
        }

        public UserTakenCourse toEntity() {
            return UserTakenCourse.builder()
                    .year(this.year)
                    .semester(this.semester)
                    .courseType(this.courseType)
                    .courseName(this.courseName)
                    .courseCode(this.courseCode)
                    .credit(this.credit)
                    .grade(this.grade)
                    .build();
        }
    }
}
