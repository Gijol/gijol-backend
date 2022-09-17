package com.gist.graduation.requirment.domain.constants;

import com.gist.graduation.course.domain.CourseInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScienceBasicConstants {

    public static class Math {
        public static final List<CourseInfo> CALCULUS = List.of(
                new CourseInfo("고급미적분학과 응용", "GS1011", "3"),
                new CourseInfo("미적분학과 응용", "GS1001", "3")
        );

        public static final List<CourseInfo> CORE_MATH = List.of(
                new CourseInfo("다변수해석학과 응용", "GS1002", "3"),
                new CourseInfo("다변수해석학과 응용", "GS2001", "3"),
                new CourseInfo("다변수해석학과 응용", "MM2001", "3"),
                new CourseInfo("고급다변수해석학과 응용", "GS1012", "3"),
                new CourseInfo("선형대수학과 응용", "GS2004", "3"),
                new CourseInfo("기초 미분방정식과 선형대수의 응용", "GS2013", "3"),
                new CourseInfo("선형대수학", "GS2004", "3"),
                new CourseInfo("선형대수학과 응용", "MM2004", "3"),
                new CourseInfo("미분방정식과 응용", "GS2002", "3"),
                new CourseInfo("미분방정식과 응용", "MM2002", "3")
        );
    }

    public static class Science {
        public static final List<CourseInfo> PHYSICS = List.of(
                new CourseInfo("일반물리학 및 연습 I", "GS1101", "3"),
                new CourseInfo("고급일반물리학 및 연습 I", "GS1103", "3")
        );

        public static final CourseInfo PHYSICS_EXPERIMENT = new CourseInfo("일반물리학 실험 I", "GS1111", "1");

        public static final List<CourseInfo> CHEMISTRY = List.of(
                new CourseInfo("일반화학 및 연습 I", "GS1201", "3"),
                new CourseInfo("고급일반화학 및 연습 Ⅰ", "GS1203", "3")
        );

        public static final CourseInfo CHEMISTRY_EXPERIMENT = new CourseInfo("일반화학실험 Ⅰ", "GS1211", "1");

        public static final List<CourseInfo> BIOLOGY = List.of(
                new CourseInfo("생물학", "GS1301", "3"),
                new CourseInfo("일반생물학 및 연습 Ⅰ", "GS1301", "3"),
                new CourseInfo("인간 생물학", "GS1302", "3"),
                new CourseInfo("고급일반생물학Ⅰ", "GS1303", "3"),
                new CourseInfo("고급일반생물학 및 연습 Ⅰ", "GS1303", "3")
        );

        public static final CourseInfo BIOLOGY_EXPERIMENT = new CourseInfo("일반생물학 실험", "GS1311", "1");

        public static final CourseInfo COMPUTER_PROGRAMMING = new CourseInfo("컴퓨터 프로그래밍", "GS1401", "3");

        public static final CourseInfo SOFTWARE_BASIC_AND_CODING = new CourseInfo("소프트웨어 기초와 코딩", "GS1490", "2");
    }
}
