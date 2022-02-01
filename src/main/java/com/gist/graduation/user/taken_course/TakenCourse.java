package com.gist.graduation.user.taken_course;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document
@ToString
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

    public String courseNameAndCode() {
        return this.courseName + " " + this.courseCode;
    }

}
