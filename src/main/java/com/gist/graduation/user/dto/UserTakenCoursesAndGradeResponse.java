package com.gist.graduation.user.dto;

import com.gist.graduation.user.domain.UserTakenCourse;
import com.gist.graduation.user.taken_course.CourseType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserTakenCoursesAndGradeResponse {

    private List<UserTakenCourseBySemesterResponse> userTakenCourseBySemesterResponses;
    private BigDecimal averageGrade;
    private int totalCredit;

    public UserTakenCoursesAndGradeResponse(List<UserTakenCourseBySemesterResponse> userTakenCourseBySemesterResponses, BigDecimal averageGrade, int totalCredit) {
        this.userTakenCourseBySemesterResponses = userTakenCourseBySemesterResponses;
        this.averageGrade = averageGrade == null ? BigDecimal.ZERO : averageGrade;
        this.totalCredit = totalCredit;
    }

    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    public static class UserTakenCourseBySemesterResponse {

        private int year;
        private String semester;
        private BigDecimal averageGradeBySemester;
        private List<UserTakenCourseResponse> coursesAndGradeResponses;

        public UserTakenCourseBySemesterResponse(int year, String semester, BigDecimal averageGradeBySemester, List<UserTakenCourseResponse> coursesAndGradeResponses) {
            this.year = year;
            this.semester = semester;
            this.averageGradeBySemester = averageGradeBySemester == null ? BigDecimal.ZERO : averageGradeBySemester;
            this.coursesAndGradeResponses = coursesAndGradeResponses;
        }
    }

    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    public static class UserTakenCourseResponse {
        private CourseType courseType;
        private String courseName;
        private String courseCode;
        private Integer credit;
        private String grade;

        public UserTakenCourseResponse(CourseType courseType, String courseName, String courseCode, Integer credit, String grade) {
            this.courseType = courseType;
            this.courseName = courseName;
            this.courseCode = courseCode;
            this.credit = credit;
            this.grade = grade;
        }

        private static UserTakenCourseResponse from(UserTakenCourse userTakenCourse) {
            return new UserTakenCourseResponse(userTakenCourse.getCourseType(), userTakenCourse.getCourseName(), userTakenCourse.getCourseCode(), userTakenCourse.getCredit(), userTakenCourse.getGrade());
        }

        public static List<UserTakenCourseResponse> listFrom(List<UserTakenCourse> userTakenCourse) {
            return userTakenCourse.stream()
                    .map(UserTakenCourseResponse::from)
                    .collect(Collectors.toList());

        }
    }
}
