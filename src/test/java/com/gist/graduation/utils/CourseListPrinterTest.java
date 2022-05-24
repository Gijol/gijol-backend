package com.gist.graduation.utils;

import com.gist.graduation.printer.TestCourseListPrinter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

public class CourseListPrinterTest {

    protected TestCourseListPrinter testCourseListPrinter;
    protected List<RegisteredCourse> courseList;

    @BeforeEach
    void setup() {
        testCourseListPrinter = new TestCourseListPrinter();
        courseList = CourseListParser.getCourseList();
    }

    @Test
    public void englishPrinterTest() {
        testCourseListPrinter.printCoreEnglishCourses(courseList);
    }

    @Test
    private void writingPrinterTest() {
        testCourseListPrinter.printCoreWritingClass(courseList);
    }

    @Test
    void calculusPrinterTest() {
        testCourseListPrinter.printCalculusClass(courseList);
    }

    @Test
    void coreMathTest() {
        testCourseListPrinter.printClassByCreditAndName(courseList, "다변수", 3);
        testCourseListPrinter.printClassByCreditAndName(courseList, "선형대수학", 3);
        testCourseListPrinter.printClassByCreditAndName(courseList, " 미분방정식", 3);
    }

    @Test
    void coreScienceTest() {
        testCourseListPrinter.printClassByCreditAndName(courseList, "일반물리학", 3);
        testCourseListPrinter.printClassByCreditAndName(courseList, "실험", 1);
        testCourseListPrinter.printClassByCreditAndName(courseList, "일반화학", 3);
        testCourseListPrinter.printClassByCreditAndNameAndCode(courseList, "생물학", 3, "GS");
        testCourseListPrinter.printClassByCreditAndName(courseList, "컴퓨터 프로그래밍", 3);
    }

    @Test
    void mathPrinter() {
        testCourseListPrinter.printClassByCreditAndName(courseList, "미분", 3);
    }

    @Test
    void softwareBasicPrinter() {
        testCourseListPrinter.printClassByCreditAndName(courseList, "소프트웨어", 2);
    }

}