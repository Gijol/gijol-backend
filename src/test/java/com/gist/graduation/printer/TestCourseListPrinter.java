package com.gist.graduation.printer;

import com.gist.graduation.course.domain.CourseInfo;
import com.gist.graduation.utils.RegisteredCourse;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TestCourseListPrinter {

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

    public void printCalculusClass(List<RegisteredCourse> registeredCourseList) {
        Set<RegisteredCourse> writingConditionCourse = registeredCourseList.stream()
                .filter(s -> s.getName().contains("미적분"))
                .filter(s -> s.getCredit() == 3)
                .collect(Collectors.toSet());
        for (RegisteredCourse registeredCourse : writingConditionCourse) {
            printCoursePretty(registeredCourse);
        }
    }

    public void printClassByCreditAndName(List<RegisteredCourse> registeredCourseList, String name, int credit) {
        Set<RegisteredCourse> conditionCourse = registeredCourseList.stream()
                .filter(s -> s.getName().contains(name))
                .filter(s -> s.getCredit() == credit)
                .collect(Collectors.toSet());
        for (RegisteredCourse registeredCourse : conditionCourse) {
            printCoursePretty(registeredCourse);
        }
    }

    public void printClassByCreditAndNameAndCode(List<RegisteredCourse> registeredCourseList, String name, int credit, String code) {
        Set<RegisteredCourse> conditionCourse = registeredCourseList.stream()
                .filter(s -> s.getName().contains(name))
                .filter(s -> s.getCredit() == credit)
                .filter(s -> s.getCode().contains(code))
                .collect(Collectors.toSet());
        for (RegisteredCourse registeredCourse : conditionCourse) {
            printCoursePretty(registeredCourse);
        }
    }

    public void printMandatoryMajorClass(List<RegisteredCourse> registeredCourseList, String code) {
        Set<RegisteredCourse> conditionCourse = registeredCourseList.stream()
                .filter(s -> s.getCode().contains(code))
                .filter(s -> s.getType().equals("필수"))
                .collect(Collectors.toSet());

        for (RegisteredCourse registeredCourse : conditionCourse) {
            printCoursePretty(registeredCourse);
        }
    }

    public void printByCode(List<RegisteredCourse> registeredCourseList, String code) {
        Set<RegisteredCourse> conditionCourse = registeredCourseList.stream()
                .filter(s -> s.getCode().contains(code))
                .collect(Collectors.toSet());

        for (RegisteredCourse registeredCourse : conditionCourse) {
            printCoursePretty(registeredCourse);
        }
    }

    public void printCoursePretty(RegisteredCourse registeredCourse) {
        System.out.print("new TakenCourse(");
        System.out.printf("\"%s\", ", registeredCourse.getName());
        System.out.printf("\"%s\", ", registeredCourse.getCode());
        System.out.printf("\"%s\")\n", registeredCourse.getCredit());
    }

    public void printCourseCollectionPretty(Collection<RegisteredCourse> registeredCourse) {
        for (RegisteredCourse course : registeredCourse) {
            System.out.print("new TakenCourse(");
            System.out.printf("\"%s\", ", course.getName());
            System.out.printf("\"%s\", ", course.getCode());
            System.out.printf("\"%s\"),\n", course.getCredit());
        }
    }

    public void printTakenCourseCollectionPretty(Collection<CourseInfo> takenCourses) {
        for (CourseInfo course : takenCourses) {
            System.out.print("new TakenCourse(");
            System.out.printf("\"%s\", ", course.getCourseName());
            System.out.printf("\"%s\", ", course.getCourseCode());
            System.out.printf("\"%s\"),\n", course.getCourseCredit());
        }
    }


}
