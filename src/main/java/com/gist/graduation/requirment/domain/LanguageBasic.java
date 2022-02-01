package com.gist.graduation.requirment.domain;

import com.gist.graduation.user.taken_course.TakenCourse;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Document
public class LanguageBasic extends RequirementStatusBaseEntity {

    public void checkRequirementByStudentId(Integer studentId, UserTakenCoursesList inputUserTakenCoursesList) {
        if (studentId >= 18) {
            checkEnlgishFrom2018(inputUserTakenCoursesList);
            checkWritingFrom2018(inputUserTakenCoursesList);
        }

        if (getMessage().isEmpty()) {
            isSatisfied();
        }

        addCredit(this.getUserTakenCoursesList().sumCreditOfCourses());
    }

    private void checkEnlgishFrom2018(UserTakenCoursesList inputUserTakenCoursesList) {
        TakenCourse english1 = new TakenCourse("영어 Ⅰ: 신입생 영어", "GS1601", "2");
        TakenCourse englishPresentation = new TakenCourse("영어 Ⅰ: 발표와 토론", "GS1603", "2");
        TakenCourse english2 = new TakenCourse("영어 II : 이공계 글쓰기 입문", "GS2652", "2");

        UserTakenCoursesList userTakenCoursesList = this.getUserTakenCoursesList();

        userTakenCoursesList.addAll(inputUserTakenCoursesList.getTakenCourses()
                .stream()
                .filter(s -> s.equals(english1) || s.equals(englishPresentation) || s.equals(english2))
                .collect(Collectors.toList())
        );

        if (userTakenCoursesList.notExist(english1) && userTakenCoursesList.notExist(englishPresentation)) {
            getMessage().add(String.format("%s 또는 %s을 수강해야 합니다.", english1.courseNameAndCode(), englishPresentation.courseNameAndCode()));
        }

        if (inputUserTakenCoursesList.notExist(english2)) {
            getMessage().add(String.format("%s를 수강해야 합니다.", english2.courseNameAndCode()));
        }

    }

    private void checkWritingFrom2018(UserTakenCoursesList inputUserTakenCoursesList) {
        List<TakenCourse> writingCourses = new ArrayList<>();
        writingCourses.add(new TakenCourse("글쓰기의 기초: 학술적 글쓰기", "GS1512", "3"));
        writingCourses.add(new TakenCourse("글쓰기의 기초: 논리적 글쓰기", "GS1511", "3"));
        writingCourses.add(new TakenCourse("글쓰기의 기초: 창의적 글쓰기", "GS1513", "3"));
        writingCourses.add(new TakenCourse("심화 글쓰기: 과학 글쓰기", "GS1531", "3"));
        writingCourses.add(new TakenCourse("심화 글쓰기: 고전 읽기와 글쓰기", "GS1532", "3"));
        writingCourses.add(new TakenCourse("심화 글쓰기: 비평적 글쓰기", "GS1533", "3"));
        writingCourses.add(new TakenCourse("심화 글쓰기: 디지털 스토리텔링", "GS1534", "3"));

        List<TakenCourse> userTakenCourses = inputUserTakenCoursesList.getTakenCourses()
                .stream()
                .filter(writingCourses::contains)
                .collect(Collectors.toList());

        if (userTakenCourses.isEmpty()) {
            getMessage().add(String.format("%s 중 한 과목을 수강해야 합니다.", writingCourses));
        }

        getUserTakenCoursesList().addAll(userTakenCourses);
    }


}
