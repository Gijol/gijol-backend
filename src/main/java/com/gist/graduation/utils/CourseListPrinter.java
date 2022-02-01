package com.gist.graduation.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CourseListPrinter {

    public void printCoreEnglishCourses(List<RegisteredCourse> registeredCourseList) {
        Set<RegisteredCourse> englishConditionCourse = registeredCourseList.stream()
                .filter(s -> s.getName().contains("영어 Ⅰ") || s.getName().contains("영어 II"))
                .filter(s -> s.getCode().contains("GS1") || s.getCode().contains("GS2"))
                .filter(s -> s.getCredit() == 2)
                .collect(Collectors.toSet());
        for (RegisteredCourse registeredCourse : englishConditionCourse) {
            printCoursePretty(registeredCourse);
        }
    }

    public void printCoreWritingClass(List<RegisteredCourse> registeredCourseList) {
        Set<RegisteredCourse> writingConditionCourse = registeredCourseList.stream()
                .filter(s -> s.getName().contains("글쓰기"))
                .filter(s -> s.getCredit() == 3)
                .collect(Collectors.toSet());
        for (RegisteredCourse course : writingConditionCourse) {
            printCoursePretty(course);
        }
    }

    public void printCalculusClass(List<RegisteredCourse> registeredCourseList){
        Set<RegisteredCourse> writingConditionCourse = registeredCourseList.stream()
                .filter(s -> s.getName().contains("미적분"))
                .filter(s -> s.getCredit() == 3)
                .collect(Collectors.toSet());
        for (RegisteredCourse registeredCourse : writingConditionCourse) {
            printCoursePretty(registeredCourse);
        }
    }

    public void printClassByCreditAndName(List<RegisteredCourse> registeredCourseList, String name, int credit){
        Set<RegisteredCourse> conditionCourse = registeredCourseList.stream()
                .filter(s -> s.getName().contains(name))
                .filter(s -> s.getCredit() == credit)
                .collect(Collectors.toSet());
        for (RegisteredCourse registeredCourse : conditionCourse) {
            printCoursePretty(registeredCourse);
        }
    }

    private void printCoursePretty(RegisteredCourse registeredCourse) {
        System.out.printf("\"%s\", ", registeredCourse.getName());
        System.out.printf("\"%s\", ", registeredCourse.getCode());
        System.out.printf("\"%s\"\n", registeredCourse.getCredit());

    }


}
