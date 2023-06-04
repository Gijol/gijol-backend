package com.gist.graduation.user.application;

import com.gist.graduation.user.domain.UserTakenCourse;
import com.gist.graduation.user.taken_course.CourseType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GraduationCalculatorTest {

    private final GraduationCalculator graduationCalculator = new GraduationCalculator();

    @Test
    void calculateTotalAverageGrade() {
        //given list usertakencourse
        List<UserTakenCourse> userTakenCourses = List.of(new UserTakenCourse(2020, "1학기", CourseType.필수, "컴퓨터 프로그래밍", "GS1234", 3, "A+"),
                new UserTakenCourse(2020, "1학기", CourseType.필수, "일반 화학 실험", "GS1235", 1, "B0"),
                new UserTakenCourse(2020, "1학기", CourseType.필수, "일반 물리 실험", "GS1235", 1, "A0"),
                new UserTakenCourse(2020, "1학기", CourseType.필수, "소프트웨어 활용과 코딩", "GS1235", 2, "A+"),
                new UserTakenCourse(2020, "1학기", CourseType.PPE, "인간의 마음과 행동들", "GS2239", 3, "C0")
        );

        BigDecimal averageGrade = graduationCalculator.calculateTotalAverageGrade(userTakenCourses);

        Assertions.assertThat(averageGrade).isEqualTo(new BigDecimal("3.55"));
    }

    @Test
    void calculateTotalAverageGradeWithF() {
        //given list usertakencourse
        List<UserTakenCourse> userTakenCourses = List.of(new UserTakenCourse(2020, "1학기", CourseType.필수, "컴퓨터 프로그래밍", "GS1234", 3, "A+"),
                new UserTakenCourse(2020, "1학기", CourseType.필수, "일반 화학 실험", "GS1235", 1, "B0"),
                new UserTakenCourse(2020, "1학기", CourseType.필수, "일반 물리 실험", "GS1235", 1, "A0"),
                new UserTakenCourse(2020, "1학기", CourseType.필수, "소프트웨어 활용과 코딩", "GS1235", 2, "A+"),
                new UserTakenCourse(2020, "1학기", CourseType.PPE, "인간의 마음과 행동들", "GS2239", 3, "C0"),
                new UserTakenCourse(2020, "1학기", CourseType.PPE, "소프트웨어 공학", "GS4239", 3, "F")
        );

        BigDecimal averageGrade = graduationCalculator.calculateTotalAverageGrade(userTakenCourses);

        Assertions.assertThat(averageGrade).isEqualTo(new BigDecimal("2.73"));
    }
}