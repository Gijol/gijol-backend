package com.gist.graduation.requirment.domain;

import com.gist.graduation.requirment.domain.major.Major;
import com.gist.graduation.requirment.domain.major.MajorType;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;
import com.gist.graduation.utils.UserTakenCousrseParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

class MajorTest {

    private UserTakenCoursesList takenCourses;
    private Integer studentId;

    @BeforeEach
    void setup() throws IOException {
        ClassPathResource gradeResource = new ClassPathResource("test-grade/grade_report.xls");
        File file = gradeResource.getFile();
        takenCourses = UserTakenCousrseParser.parseUserTakenCourse(file);
        studentId = UserTakenCousrseParser.getStudentId(file);
    }

    @Test
    void checkRequirementByStudentId() {
        Major major = new Major();
        major.checkRequirementByStudentId(studentId, takenCourses, MajorType.EC);
        System.out.println(major.getUserTakenCoursesList().toString());
        System.out.println(major.getTotalCredits());
    }
}