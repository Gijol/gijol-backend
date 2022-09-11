package com.gist.graduation.course.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CourseInfo {
    private String courseCode;
    private String courseName;
    private int courseCredit;

    public CourseInfo(String courseCode, String courseName, int courseCredit) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.courseCredit = courseCredit;
    }
}
