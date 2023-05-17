package com.gist.graduation.user.domain;

import com.gist.graduation.common.BaseEntity;
import com.gist.graduation.user.domain.vo.LetterGrade;
import com.gist.graduation.user.domain.vo.YearAndSemester;
import com.gist.graduation.user.taken_course.CourseType;
import com.gist.graduation.user.taken_course.TakenCourse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserTakenCourse extends BaseEntity {

    @Embedded
    private YearAndSemester yearAndSemester;
    private CourseType courseType;
    private String courseName;
    private String courseCode;
    private Integer credit;
    private String grade;

    @Builder
    public UserTakenCourse(int year, String semester, CourseType courseType, String courseName, String courseCode, Integer credit, String grade) {
        this.yearAndSemester = new YearAndSemester(year, semester);
        this.courseType = courseType;
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.credit = credit;
        this.grade = grade;
    }

    public TakenCourse toTakenCourse(){
        return TakenCourse.builder()
                .year(this.getYear())
                .semester(this.getSemester())
                .courseType(this.courseType)
                .courseName(this.courseName)
                .courseCode(this.courseCode)
                .credit(this.credit.toString())
                .build();
    }

    public BigDecimal multiplyCreditAndGrade(){
        return LetterGrade.getGradePointByGrade(this.grade).multiply(BigDecimal.valueOf(this.credit));
    }

    public int getYear() {
        return this.yearAndSemester.getYear();
    }

    public String getSemester() {
        return this.yearAndSemester.getSemester();
    }
}
