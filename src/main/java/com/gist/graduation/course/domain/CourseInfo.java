package com.gist.graduation.course.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CourseInfo)) return false;
        CourseInfo that = (CourseInfo) o;
        return courseCredit == that.courseCredit && courseCode.equals(that.courseCode) && courseName.equals(that.courseName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseCode, courseName, courseCredit);
    }
}
