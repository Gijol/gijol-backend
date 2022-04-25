package com.gist.graduation.utils;

import com.gist.graduation.requirment.domain.MajorType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Set;

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

    @Test
    void coreScienceTest() {
        courseListPrinter.printClassByCreditAndName(courseList, "일반물리학", 3);
        courseListPrinter.printClassByCreditAndName(courseList, "실험", 1);
        courseListPrinter.printClassByCreditAndName(courseList, "일반화학", 3);
        courseListPrinter.printClassByCreditAndNameAndCode(courseList, "생물학", 3, "GS");
        courseListPrinter.printClassByCreditAndName(courseList, "컴퓨터 프로그래밍", 3);
    }

    @Test
    void physicsPrintTest() {
        courseListPrinter.printByCode(courseList, "PS");
    }

    @Test
    void printMajor() throws IOException {
        for (MajorType majorType : MajorType.values()) {
            Set<RegisteredCourse> courseList = courseListParser.getMajorCourseList(majorType.name());
            courseListPrinter.printCourseCollectionPretty(courseList);
            System.out.println();
        }
    }

}