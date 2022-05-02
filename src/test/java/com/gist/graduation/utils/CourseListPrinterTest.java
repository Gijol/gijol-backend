package com.gist.graduation.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class CourseListPrinterTest {

    private CourseListPrinter courseListPrinter;
    private CourseListParser courseListParser;
    private List<RegisteredCourse> courseList;

    @BeforeEach
    void setup() throws IOException {
        courseListParser = new CourseListParser();
        courseListPrinter = new CourseListPrinter();
        courseList = courseListParser.getCourseList();
    }

    @Test
    void englishPrinterTest() {
        courseListPrinter.printCoreEnglishCourses(courseList);
    }

    @Test
    void writingPrinterTest() {
        courseListPrinter.printCoreWritingClass(courseList);
    }

    @Test
    void calculusPrinterTest() {
        courseListPrinter.printCalculusClass(courseList);
    }

    @Test
    void coreMathTest() {
        courseListPrinter.printClassByCreditAndName(courseList, "다변수", 3);
        courseListPrinter.printClassByCreditAndName(courseList, "선형대수학", 3);
        courseListPrinter.printClassByCreditAndName(courseList, " 미분방정식", 3);
    }

    @Test
    void coreScienceTest() {
        courseListPrinter.printClassByCreditAndName(courseList, "일반물리학", 3);
        courseListPrinter.printClassByCreditAndName(courseList, "실험", 1);
        courseListPrinter.printClassByCreditAndName(courseList, "일반화학", 3);
        courseListPrinter.printClassByCreditAndNameAndCode(courseList, "생물학", 3, "GS");
        courseListPrinter.printClassByCreditAndName(courseList, "컴퓨터 프로그래밍", 3);
    }

    @Test
    void humanitiesTest() {
        courseListPrinter.printTakenCourseCollectionPretty(CourseListParser.getHumanitiesCoursesList()
                .stream()
                .sorted((a, b) -> a.getCourseCode().compareTo(b.getCourseCode()))
                .collect(Collectors.toList()));
    }

    @Test
    void humanitiesWithoutGSCTest() {
        courseListPrinter.printTakenCourseCollectionPretty(CourseListParser.getHumanitiesWithoutGSC()
                .stream()
                .sorted((a, b) -> a.getCourseCode().compareTo(b.getCourseCode()))
                .collect(Collectors.toList()));
    }

    @Test
    void GSCTest() {
        List<String> gscCode = List.of("GS25", "GS26", "GS27", "GS28", "GS29", "GS35");
        courseListPrinter.printTakenCourseCollectionPretty(CourseListParser.getHumanitiesCoursesList()
                .stream()
                .filter(s -> gscCode.stream().anyMatch(t -> s.getCourseCode().contains(t)))
                .sorted((a, b) -> a.getCourseCode().compareTo(b.getCourseCode()))
                .collect(Collectors.toList()));
    }

    @Test
    void ETCTest() {
        String artCode = "GS02";
        String sportCode = "GS01";
        courseListPrinter.printByCode(courseList, artCode);
        courseListPrinter.printByCode(courseList, sportCode);
    }

    @Test
    void colloquiumTest() {
        String name = "콜로퀴움";
        courseListPrinter.printClassByCreditAndName(courseList, name, 0);
    }

    @Test
    void researchTest() {
        String name = "연구";
//        courseListPrinter.printClassByCreditAndName(courseList, name, 3);

        courseListPrinter.printByCode(courseList, "910");
    }

    @Test
    void freshmanMandatoryTest() {
        String freshMan = "새내기";
        String findMajorCourse = "전공탐색";
        courseListPrinter.printClassByCreditAndName(courseList, freshMan, 1);
        courseListPrinter.printClassByCreditAndName(courseList, "세미나", 1);
        courseListPrinter.printClassByCreditAndName(courseList, findMajorCourse, 1);
    }



}