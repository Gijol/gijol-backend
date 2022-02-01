package com.gist.graduation.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

class CourseListPrinterTest {

    private CourseListPrinter courseListPrinter;
    private CourseListParser courseListParser;

    @BeforeEach
    void setup() {
        courseListParser = new CourseListParser();
        courseListPrinter = new CourseListPrinter();
    }

    @Test
    void englishPrinterTest() throws IOException {
        List<RegisteredCourse> courseList = courseListParser.getCourseList();
        courseListPrinter.printCoreEnglishCourses(courseList);
    }

    @Test
    void writingPrinterTest() throws IOException {
        List<RegisteredCourse> courseList = courseListParser.getCourseList();
        courseListPrinter.printCoreWritingClass(courseList);
    }

}