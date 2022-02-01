package com.gist.graduation.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

class CourseListPrinterTest {

    private CourseListPrinter courseListPrinter;
    private CourseListParser courseListParser;
    private List<RegisteredCourse> courseList;

    @BeforeEach
    void setup() throws IOException{
        courseListParser = new CourseListParser();
        courseListPrinter = new CourseListPrinter();
        courseList = courseListParser.getCourseList();
    }

    @Test
    void englishPrinterTest() {
        courseListPrinter.printCoreEnglishCourses(courseList);
    }

    @Test
    void writingPrinterTest(){
        courseListPrinter.printCoreWritingClass(courseList);
    }

    @Test
    void calculusPrinterTest(){
        courseListPrinter.printCalculusClass(courseList);
    }

    @Test
    void coreMathTest() {
        courseListPrinter.printClassByCreditAndName(courseList, "다변수", 3);
        courseListPrinter.printClassByCreditAndName(courseList, "선형대수학", 3);
        courseListPrinter.printClassByCreditAndName(courseList, " 미분방정식", 3);
    }

}