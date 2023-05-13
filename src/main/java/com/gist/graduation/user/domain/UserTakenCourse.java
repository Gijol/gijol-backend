package com.gist.graduation.user.domain;

import com.gist.graduation.common.BaseEntity;
import com.gist.graduation.user.taken_course.CourseType;
import com.gist.graduation.user.taken_course.TakenCourse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserTakenCourse extends BaseEntity {

    private int year;
    private String semester;
    private CourseType courseType;
    private String courseName;
    private String courseCode;
    private Integer credit;
    private String grade;

    @Builder
    public UserTakenCourse(int year, String semester, CourseType courseType, String courseName, String courseCode, Integer credit, String grade) {
        this.year = year;
        this.semester = semester;
        this.courseType = courseType;
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.credit = credit;
        this.grade = grade;
    }

    public TakenCourse toTakenCourse(){
        return TakenCourse.builder()
                .year(this.year)
                .semester(this.semester)
                .courseType(this.courseType)
                .courseName(this.courseName)
                .courseCode(this.courseCode)
                .credit(this.credit.toString())
                .build();
    }

}
