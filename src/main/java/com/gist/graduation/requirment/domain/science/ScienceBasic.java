package com.gist.graduation.requirment.domain.science;


import com.gist.graduation.requirment.domain.RequirementStatusBaseEntity;
import com.gist.graduation.user.taken_course.TakenCourse;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;

import java.util.*;
import java.util.stream.Collectors;

public class ScienceBasic extends RequirementStatusBaseEntity {

    public static final int SCIENCE_AND_EXPERIMENT_PAIR = 2;

    public void checkRequirementByStudentId(Integer studentId, UserTakenCoursesList inputUserTakenCoursesList) {
        if (studentId >= 18) {
            checkMathFrom2018(inputUserTakenCoursesList);
        }

        if (this.getMessages().isEmpty()) {
            isSatisfied();
        }

        addCredit(this.getUserTakenCoursesList().sumCreditOfCourses());
    }

    private void checkMathFrom2018(UserTakenCoursesList inputUserTakenCoursesList) {

        List<TakenCourse> calculus = new ArrayList<>(
                Arrays.asList(
                        new TakenCourse("고급미적분학과 응용", "GS1011", "3"),
                        new TakenCourse("미적분학과 응용", "GS1001", "3")
                ));

        List<TakenCourse> coreMath = new ArrayList<>(
                Arrays.asList(
                        new TakenCourse("다변수해석학과 응용", "GS1002", "3"),
                        new TakenCourse("다변수해석학과 응용", "GS2001", "3"),
                        new TakenCourse("다변수해석학과 응용", "MM2001", "3"),
                        new TakenCourse("고급다변수해석학과 응용", "GS1012", "3"),
                        new TakenCourse("선형대수학과 응용", "GS2004", "3"),
                        new TakenCourse("기초 미분방정식과 선형대수의 응용", "GS2013", "3"),
                        new TakenCourse("선형대수학", "GS2004", "3"),
                        new TakenCourse("선형대수학과 응용", "MM2004", "3")
                ));

        List<TakenCourse> userTakenMathCourses = inputUserTakenCoursesList.getTakenCourses()
                .stream()
                .filter(s -> calculus.contains(s) || coreMath.contains(s))
                .collect(Collectors.toList());

        if (userTakenMathCourses.stream().noneMatch(calculus::contains)) {
            this.getMessages().add(String.format("%s 중 한 과목을 수강해야 합니다.", calculus));
        }

        if (userTakenMathCourses.stream().noneMatch(coreMath::contains)) {
            this.getMessages().add(String.format("%s 중 한 과목을 수강해야 합니다.", coreMath));
        }

        getUserTakenCoursesList().addAll(userTakenMathCourses);
    }

    private void checkScienceFrom2018() {

        List<TakenCourse> checkedCourse = new ArrayList<>();
        Map<String, Boolean> scienceMap = new TreeMap<>();

        List<TakenCourse> physics = Arrays.asList(
                new TakenCourse("일반물리학 및 연습 I", "GS1101", "3"),
                new TakenCourse("고급일반물리학 및 연습 I", "GS1103", "3")
        );

        TakenCourse physicsExperiment = new TakenCourse("일반물리학 실험 I", "GS1111", "1");

        List<TakenCourse> chemistry = Arrays.asList(
                new TakenCourse("일반화학 및 연습 I", "GS1201", "3"),
                new TakenCourse("고급일반화학 및 연습 Ⅰ", "GS1203", "3")
        );

        TakenCourse chemistryExperiment = new TakenCourse("일반화학실험 Ⅰ", "GS1211", "1");

        List<TakenCourse> biology = Arrays.asList(
                new TakenCourse("생물학", "GS1301", "3"),
                new TakenCourse("일반생물학 및 연습 Ⅰ", "GS1301", "3"),
                new TakenCourse("인간 생물학", "GS1302", "3"),
                new TakenCourse("고급일반생물학Ⅰ", "GS1303", "3"),
                new TakenCourse("고급일반생물학 및 연습 Ⅰ", "GS1303", "3")
        );

        TakenCourse biologyExperiment = new TakenCourse("일반생물학 실험", "GS1311", "1");

        TakenCourse computerProgramming = new TakenCourse("컴퓨터 프로그래밍", "GS1401", "3");
    }

    private List<TakenCourse> checkEachScience(Map<String, Object> scienceMap, UserTakenCoursesList inputUserTakenCourse, List<TakenCourse> science, TakenCourse scienceExperiment) {
        List<TakenCourse> scienceCourses = inputUserTakenCourse.getTakenCourses()
                .stream()
                .filter(s -> science.contains(s) || scienceExperiment.equals(s))
                .collect(Collectors.toList());

        if (scienceCourses.size() != SCIENCE_AND_EXPERIMENT_PAIR) {
//            hello.getMessage()
        }
        return new ArrayList<>();
    }


}
