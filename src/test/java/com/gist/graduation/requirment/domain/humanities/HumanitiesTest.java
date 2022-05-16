package com.gist.graduation.requirment.domain.humanities;

import com.gist.graduation.requirment.domain.major.MajorType;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;
import com.gist.graduation.utils.UserTakenCousrseParser;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

class HumanitiesTest {

    @Test
    void checkRequirementByStudentId() {
        Humanities humanities = new Humanities();
        humanities.checkRequirementByStudentId(21, getUserTakenCourses("test-grade/grade_report.xls"), MajorType.EC);
        System.out.println(humanities);
    }

    @Test
    void checkother2() {
        Humanities humanities = new Humanities();
        humanities.checkRequirementByStudentId(21, getUserTakenCourses("/test-grade/승규_성적.xls"), MajorType.EC);
        System.out.println(humanities);
    }

    private UserTakenCoursesList getUserTakenCourses(String path) {
        ClassPathResource classPathResource = new ClassPathResource(path);
        try {
            return UserTakenCousrseParser.parseUserTakenCousrse(classPathResource.getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new UserTakenCoursesList();
    }
}