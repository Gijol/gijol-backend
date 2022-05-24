package com.gist.graduation.utils;

import com.gist.graduation.printer.TestCourseListPrinter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static com.gist.graduation.requirment.domain.constants.MajorMandatoryConstants.Chemistry.CH;
import static com.gist.graduation.requirment.domain.constants.MajorMandatoryConstants.Environment.EV;
import static com.gist.graduation.requirment.domain.constants.MajorMandatoryConstants.MaterialScience.MA;
import static com.gist.graduation.requirment.domain.constants.MajorMandatoryConstants.MechanicalEngineering.MC;

public class MajorCoursesListPrinterTest {

    private TestCourseListPrinter testCourseListPrinter;
    private CourseListParser courseListParser;
    private List<RegisteredCourse> courseList;

    @BeforeEach
    void setup() throws IOException {
        courseListParser = new CourseListParser();
        testCourseListPrinter = new TestCourseListPrinter();
        courseList = CourseListParser.getCourseList();
    }

    @Test
    void physicsPrintTest() {
        testCourseListPrinter.printByCode(courseList, "PS");
    }

    @Test
    void ChemistryPrintTest() {
        testCourseListPrinter.printByCode(courseList, CH);
    }

    @Test
    void EnvironmentPrintTest() {
        testCourseListPrinter.printMandatoryMajorClass(courseList, EV);
    }

    @Test
    void materialSciencePrintTest() {
        testCourseListPrinter.printMandatoryMajorClass(courseList, MA);
    }

    @Test
    void mechanicalEngineeringPrintTest() {
        testCourseListPrinter.printMandatoryMajorClass(courseList, MC);
    }
}
