package com.gist.graduation.course.domain;

import com.gist.graduation.utils.RegisteredCourse;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CourseInfo {

    @Column(name = "course_code")
    private String courseCode;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "course_credit")
    private int courseCredit;

    public CourseInfo(String courseName, String courseCode, int courseCredit) {
        this.courseName = courseName;
        this.courseCode = courseCode;
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

    public boolean belongToCoursesCode(List<String> coursesCode) {
        return coursesCode.stream()
                .anyMatch(s -> this.courseCode.contains(s));
    }

    public boolean isMinor(String code) {
        if (this.courseCode.contains("GS")) {
            return this.courseCode.substring(2).equals(code.substring(2));
        }
        return false;
    }

    public boolean isMinor(List<String> code) {
        if (this.courseCode.contains("GS")) {
            return code.stream().anyMatch(s -> s.substring(2).equals(this.courseCode.substring(2)));
        }
        return false;
    }

    public static List<CourseInfo> from(Set<RegisteredCourse> registeredCourses) {
        return registeredCourses.stream()
                .map(RegisteredCourse::toCourseInfo)
                .collect(Collectors.toList());
    }
}
