package com.gist.graduation.requirment;

import com.gist.graduation.user.taken_course.UserTakenCoursesList;
import com.gist.graduation.utils.UserTakenCousrseParser;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

public class UserConditionTest {
    protected UserTakenCoursesList takenCourses;
    protected Integer studentId;

    @BeforeEach
    void setup() throws IOException {
        ClassPathResource gradeResource = new ClassPathResource("test-grade/grade_report.xls");
        File file = gradeResource.getFile();
        takenCourses = UserTakenCousrseParser.parseUserTakenCousrse(file);
        studentId = UserTakenCousrseParser.getStudentId(file);
    }
}
