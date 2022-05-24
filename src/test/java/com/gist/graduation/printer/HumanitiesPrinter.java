package com.gist.graduation.printer;

import com.gist.graduation.utils.CourseListParser;
import com.gist.graduation.utils.CourseListPrinterTest;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

public class HumanitiesPrinter extends CourseListPrinterTest {

    @Test
    void humanitiesTest() {
        testCourseListPrinter.printTakenCourseCollectionPretty(CourseListParser.getHumanitiesCoursesList()
                .stream()
                .sorted((a, b) -> a.getCourseCode().compareTo(b.getCourseCode()))
                .collect(Collectors.toList()));
    }

    @Test
    void humanitiesWithoutGSCTest() {
        testCourseListPrinter.printTakenCourseCollectionPretty(CourseListParser.getHumanitiesWithoutGSC()
                .stream()
                .sorted((a, b) -> a.getCourseCode().compareTo(b.getCourseCode()))
                .collect(Collectors.toList()));
    }

    @Test
    void GSCTest() {
        List<String> gscCode = List.of("GS25", "GS26", "GS27", "GS28", "GS29", "GS35");
        testCourseListPrinter.printTakenCourseCollectionPretty(CourseListParser.getHumanitiesCoursesList()
                .stream()
                .filter(s -> gscCode.stream().anyMatch(t -> s.getCourseCode().contains(t)))
                .sorted((a, b) -> a.getCourseCode().compareTo(b.getCourseCode()))
                .collect(Collectors.toList()));
    }
}
