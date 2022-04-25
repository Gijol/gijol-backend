package com.gist.graduation.requirment.domain;

import com.gist.graduation.user.taken_course.TakenCourse;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;
import com.gist.graduation.utils.CourseListParser;
import com.gist.graduation.utils.UserTakenCousrseParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MajorTest {

    private UserTakenCoursesList takenCourses;
    private Integer studentId;
    private UserTakenCousrseParser userTakenCousrseParser;

    @BeforeEach
    void setup() throws IOException {
        ClassPathResource gradeResource = new ClassPathResource("test-grade/grade_report.xls");
        UserTakenCousrseParser userTakenCousrseParser = new UserTakenCousrseParser();
        File file = gradeResource.getFile();
        takenCourses = userTakenCousrseParser.parseUserTakenCousrse(file);
        studentId = userTakenCousrseParser.getStudentId(file);
    }

    @Test
    void checkRequirementByStudentId(){
        Major major = new Major();
        major.checkRequirementByStudentId(studentId, takenCourses, MajorType.EC);
        System.out.println(major.getUserTakenCoursesList().toString());
        System.out.println(major.getTotalCredits());
    }
}