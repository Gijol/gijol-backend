package com.gist.graduation.requirment.domain;

import com.gist.graduation.user.taken_course.UserTakenCoursesList;
import com.gist.graduation.utils.UserTakenCousrseParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

class MajorTest {

    private UserTakenCoursesList takenCourses;
    private Integer studentId;
    private UserTakenCousrseParser userTakenCousrseParser;

    @BeforeEach
    void setup() throws IOException {
        ClassPathResource gradeResource = new ClassPathResource("test-grade/grade_report.xls");
        UserTakenCousrseParser userTakenCousrseParser = new UserTakenCousrseParser();
        File file = gradeResource.getFile();
        takenCourses = UserTakenCousrseParser.parseUserTakenCousrse(file);
        studentId = UserTakenCousrseParser.getStudentId(file);
    }

    @Test
    void checkRequirementByStudentId() {
        Major major = new Major();
        major.checkRequirementByStudentId(studentId, takenCourses, MajorType.EC);
        System.out.println(major.getUserTakenCoursesList().toString());
        System.out.println(major.getTotalCredits());
    }

    @Test
    @DisplayName("승규 성적표 테스트")
    void physicsTest() throws IOException {
        Major major = new Major();
        getFileData("/test-grade/승규_성적.xls");
        major.checkRequirementByStudentId(studentId, takenCourses, MajorType.PS);
        System.out.println(major);

    }

    private void getFileData(String path) throws IOException {
        ClassPathResource gradeResource = new ClassPathResource(path);
        File file = gradeResource.getFile();
        takenCourses = UserTakenCousrseParser.parseUserTakenCousrse(file);
        studentId = UserTakenCousrseParser.getStudentId(file);
    }
}