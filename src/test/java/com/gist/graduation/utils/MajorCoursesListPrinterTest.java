package com.gist.graduation.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static com.gist.graduation.requirment.domain.constants.MajorMandatoryConstants.Chemistry.CH;
import static com.gist.graduation.requirment.domain.constants.MajorMandatoryConstants.Environment.EV;
import static com.gist.graduation.requirment.domain.constants.MajorMandatoryConstants.MaterialScience.MA;
import static com.gist.graduation.requirment.domain.constants.MajorMandatoryConstants.MechanicalEngineering.MC;

public class MajorCoursesListPrinterTest {

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
    void physicsPrintTest() {
        courseListPrinter.printByCode(courseList, "PS");
    }

    @Test
    void ChemistryPrintTest() {
        courseListPrinter.printByCode(courseList, CH);
    }

    @Test
    void EnvironmentPrintTest() {
        courseListPrinter.printMandatoryMajorClass(courseList, EV);
    }

    @Test
    void materialSciencePrintTest() {
        courseListPrinter.printMandatoryMajorClass(courseList, MA);
    }

    @Test
    void mechanicalEngineeringPrintTest() {
        courseListPrinter.printMandatoryMajorClass(courseList, MC);
    }
}
