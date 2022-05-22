package com.gist.graduation.requirment.domain;

import com.gist.graduation.requirment.domain.language.LanguageBasic;
import com.gist.graduation.requirment.domain.major.MajorType;
import com.gist.graduation.user.taken_course.TakenCourse;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;
import com.gist.graduation.utils.UserTakenCousrseParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LanguageBasicTest {

    private UserTakenCoursesList takenCourses;
    private Integer studentId;
    private UserTakenCousrseParser userTakenCousrseParser;

    @BeforeEach
    void setup() throws IOException {
        ClassPathResource gradeResource = new ClassPathResource("test-grade/grade_report.xls");
        UserTakenCousrseParser userTakenCousrseParser = new UserTakenCousrseParser();
        File file = gradeResource.getFile();
        takenCourses = UserTakenCousrseParser.parseUserTakenCourse(file);
        studentId = UserTakenCousrseParser.getStudentId(file);
    }

    @Test
    void checkRequirementByStudentId() {
        LanguageBasic languageBasic = new LanguageBasic();
        languageBasic.checkRequirementByStudentId(studentId, takenCourses, MajorType.EC);
        System.out.println(languageBasic.getMessages());
        System.out.println(studentId + " satisfied : " + languageBasic.getSatisfied());
        assertThat(languageBasic.getSatisfied()).isTrue();
    }

    @Test
    void checkRequirementFailDueToWritingByStudentId() {
        LanguageBasic languageBasic = new LanguageBasic();
        takenCourses.getTakenCourses().remove(new TakenCourse("글쓰기의 기초: 학술적 글쓰기", "GS1512", "3"));
        languageBasic.checkRequirementByStudentId(studentId, takenCourses, MajorType.EC);
        System.out.println(languageBasic.getMessages());
        System.out.println(studentId + " satisfied : " + languageBasic.getSatisfied());
        assertThat(languageBasic.getSatisfied()).isFalse();
    }

    @Test
    void checkRequirementFailDueToEnglishTwoByStudentId() {
        LanguageBasic languageBasic = new LanguageBasic();
        takenCourses.getTakenCourses().remove(new TakenCourse("영어 II : 이공계 글쓰기 입문", "GS2652", "2"));
        languageBasic.checkRequirementByStudentId(studentId, takenCourses, MajorType.EC);
        System.out.println(languageBasic.getMessages());
        System.out.println(studentId + " satisfied : " + languageBasic.getSatisfied());
        assertThat(languageBasic.getSatisfied()).isFalse();
    }
}