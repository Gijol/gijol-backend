package com.gist.graduation.user.taken_course;

import com.gist.graduation.utils.RegisteredCourse;
import lombok.Getter;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class TakenCourse {

    private int year;
    private String semester;
    private CourseType courseType;
    private final String courseName;
    private final String courseCode;
    private final Integer credit;

    public TakenCourse(int year, String semester, String courseType, String courseName, String courseCode, String credit) {
        this.year = year;
        this.semester = semester;
        this.courseType = CourseType.stringOf(courseType);
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.credit = Integer.parseInt(credit);
    }

    public TakenCourse(String courseName, String courseCode, String credit) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.credit = Integer.parseInt(credit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TakenCourse)) return false;
        TakenCourse that = (TakenCourse) o;
        return Objects.equals(courseName, that.courseName) && Objects.equals(courseCode, that.courseCode) && Objects.equals(credit, that.credit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseName, courseCode, credit);
    }

    @Override
    public String toString() {
        return String.format("%s(%s)", this.courseName, this.courseCode);
    }

    public void setHumanitiesType(CourseType type){
        this.courseType = type;
    }

    public static TakenCourse of(RegisteredCourse registeredCourse) {
        return new TakenCourse(registeredCourse.getName(), registeredCourse.getCode(), String.valueOf(registeredCourse.getCredit()));
    }

    public static List<TakenCourse> setToListOf(Set<RegisteredCourse> inputCourses) {
        return inputCourses.stream()
                .map(TakenCourse::of)
                .collect(Collectors.toList());
    }
}
