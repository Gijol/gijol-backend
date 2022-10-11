package com.gist.graduation.utils;

import com.gist.graduation.user.taken_course.UserTakenCoursesList;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class UserTakenCousrseParserTest {

    @Test
    void UserTakenCourseParserTest() throws IOException {
        ClassPathResource gradeResource = new ClassPathResource("test-grade/grade_report.xls");
        UserTakenCoursesList takenCourses = UserTakenCousrseParser.parseUserTakenCourse(gradeResource.getFile());
        takenCourses.getTakenCourses().forEach(System.out::println);
    }


    @Test
    public void getStdIdTest() throws IOException {
        ClassPathResource gradeResource = new ClassPathResource("test-grade/grade_report.xls");
        Integer studentId = UserTakenCousrseParser.getStudentId(gradeResource.getFile());
        assertThat(studentId).isEqualTo(20);
    }

}