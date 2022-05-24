package com.gist.graduation.printer;

import com.gist.graduation.utils.CourseListPrinterTest;
import org.junit.jupiter.api.Test;

public class EtcPrinter extends CourseListPrinterTest {

    @Test
    void colloquiumTest() {
        String name = "콜로퀴움";
        testCourseListPrinter.printClassByCreditAndName(courseList, name, 0);
    }

    @Test
    void researchTest() {
        String name = "연구";
        testCourseListPrinter.printByCode(courseList, "910");
    }

    @Test
    void artAndSportTest() {
        String artCode = "GS02";
        String sportCode = "GS01";
        testCourseListPrinter.printByCode(courseList, artCode);
        testCourseListPrinter.printByCode(courseList, sportCode);
    }

    @Test
    void freshmanMandatoryTest() {
        String freshMan = "새내기";
        String findMajorCourse = "전공탐색";
        testCourseListPrinter.printClassByCreditAndName(courseList, freshMan, 1);
        testCourseListPrinter.printClassByCreditAndName(courseList, "세미나", 1);
        testCourseListPrinter.printClassByCreditAndName(courseList, findMajorCourse, 1);
    }

    @Test
    void printVolunteerAndCreativeCulture() {
        String volunteer = "봉사";
        String creativity = "창의";
        testCourseListPrinter.printClassByCreditAndName(courseList, volunteer, 1);
        testCourseListPrinter.printClassByCreditAndName(courseList, creativity, 1);
    }

}
