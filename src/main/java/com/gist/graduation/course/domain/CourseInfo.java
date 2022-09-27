package com.gist.graduation.course.domain;

import com.gist.graduation.utils.RegisteredCourse;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CourseInfo {
    private String courseCode;
    private String courseName;
    private int courseCredit;

    public CourseInfo(String courseName, String courseCode, int courseCredit) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.courseCredit = courseCredit;
    }

    public CourseInfo(String courseName, String courseCode, String courseCredit) {
        this(courseName, courseCode, Integer.parseInt(courseCredit));
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

    @Override
    public String toString() {
        return String.format("%s(%s)", this.courseName, this.courseCode);
    }

    public static List<CourseInfo> from(Set<RegisteredCourse> registeredCourses) {
        return registeredCourses.stream()
                .map(RegisteredCourse::toCourseInfo)
                .collect(Collectors.toList());
    }
}
