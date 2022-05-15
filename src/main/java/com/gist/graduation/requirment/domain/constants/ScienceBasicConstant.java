package com.gist.graduation.requirment.domain.constants;

import com.gist.graduation.user.taken_course.TakenCourse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScienceBasicConstant {

    public static class Math {
        public static final List<TakenCourse> CALCULUS = Arrays.asList(
                new TakenCourse("고급미적분학과 응용", "GS1011", "3"),
                new TakenCourse("미적분학과 응용", "GS1001", "3")
        );

        public static final List<TakenCourse> CORE_MATH = new ArrayList<>(
                Arrays.asList(
                        new TakenCourse("다변수해석학과 응용", "GS1002", "3"),
                        new TakenCourse("다변수해석학과 응용", "GS2001", "3"),
                        new TakenCourse("다변수해석학과 응용", "MM2001", "3"),
                        new TakenCourse("고급다변수해석학과 응용", "GS1012", "3"),
                        new TakenCourse("선형대수학과 응용", "GS2004", "3"),
                        new TakenCourse("기초 미분방정식과 선형대수의 응용", "GS2013", "3"),
                        new TakenCourse("선형대수학", "GS2004", "3"),
                        new TakenCourse("선형대수학과 응용", "MM2004", "3"),
                        new TakenCourse("미분방정식과 응용", "GS2002", "3"),
                        new TakenCourse("미분방정식과 응용", "MM2002", "3")
                ));
    }

    public static class Science {
        public static final List<TakenCourse> PHYSICS = Arrays.asList(
                new TakenCourse("일반물리학 및 연습 I", "GS1101", "3"),
                new TakenCourse("고급일반물리학 및 연습 I", "GS1103", "3")
        );

        public static final TakenCourse PHYSICS_EXPERIMENT = new TakenCourse("일반물리학 실험 I", "GS1111", "1");

        public static final List<TakenCourse> CHEMISTRY = Arrays.asList(
                new TakenCourse("일반화학 및 연습 I", "GS1201", "3"),
                new TakenCourse("고급일반화학 및 연습 Ⅰ", "GS1203", "3")
        );

        public static final TakenCourse CHEMISTRY_EXPERIMENT = new TakenCourse("일반화학실험 Ⅰ", "GS1211", "1");

        public static final List<TakenCourse> BIOLOGY = Arrays.asList(
                new TakenCourse("생물학", "GS1301", "3"),
                new TakenCourse("일반생물학 및 연습 Ⅰ", "GS1301", "3"),
                new TakenCourse("인간 생물학", "GS1302", "3"),
                new TakenCourse("고급일반생물학Ⅰ", "GS1303", "3"),
                new TakenCourse("고급일반생물학 및 연습 Ⅰ", "GS1303", "3")
        );

        public static final TakenCourse BIOLOGY_EXPERIMENT = new TakenCourse("일반생물학 실험", "GS1311", "1");

        public static final TakenCourse COMPUTER_PROGRAMMING = new TakenCourse("컴퓨터 프로그래밍", "GS1401", "3");
    }
}
