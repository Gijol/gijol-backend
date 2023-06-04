package com.gist.graduation.user.dto;

import com.gist.graduation.user.domain.UserTakenCourse;
import com.gist.graduation.user.taken_course.CourseType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserTakenCoursesRequest {

    private String studentId;

    @Size(min = 1, message = "수강한 강의가 존재하지 않습니다.")
    private List<UserTakenCourseRequest> userTakenCourseList;

    public UserTakenCoursesRequest(String studentId, List<UserTakenCourseRequest> userTakenCourseList) {
        this.studentId = studentId;
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
