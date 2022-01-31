package com.gist.graduation.utils;

import com.gist.graduation.user.taken_course.TakenCourse;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.List;

class UserTakenCousrseParserTest {

    @Test
    void UserTakenCourseParserTest() throws IOException {
        ClassPathResource gradeResource = new ClassPathResource("test-grade/grade_report.xls");
        UserTakenCousrseParser userTakenCousrseParser = new UserTakenCousrseParser();
        List<TakenCourse> takenCourses = userTakenCousrseParser.parseUserTakenCousrse(gradeResource.getFile());
        System.out.println(takenCourses);
    }

}