package com.gist.graduation.user.taken_course;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@ToString
@Getter
public class TakenCourse {

    private final int year;
    private final String semester;
    private final CourseType courseType;
    private final String courseName;
    private final String courseCode;
    private final String credit;

    public TakenCourse(int year, String semester, String courseType, String courseName, String courseCode, String credit) {
        this.year = year;
        this.semester = semester;
        this.courseType = CourseType.stringOf(courseType);
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.credit = credit;
    }
}
