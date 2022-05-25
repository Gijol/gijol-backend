package com.gist.graduation.requirment.domain.major;

import com.gist.graduation.user.taken_course.CourseType;
import com.gist.graduation.user.taken_course.TakenCourse;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static com.gist.graduation.requirment.domain.constants.MajorMandatoryConstants.Chemistry.*;

@RequiredArgsConstructor
public enum ChemistryMajor {

    FROM2018(List.of(18, 19, 20, 21, 22), List.of(CH2101, CH2102, CH2103, CH2104, CH2105, CH3106, CH3107));

    private final List<Integer> studentId;
    private final List<TakenCourse> mandatoryCourses;


    public static void checkverified(UserTakenCoursesList inputUserTakenCourseList, Major major, Integer studentId) {
        for (ChemistryMajor chemistryMajor : values()) {
            if (chemistryMajor.studentId.contains(studentId)) {
                checkMandatoryCourses(inputUserTakenCourseList, major, chemistryMajor);
                addLackOfMandatoryCourses(inputUserTakenCourseList, major, chemistryMajor);
                checkElectiveCourses(inputUserTakenCourseList, major, chemistryMajor);
                return;
            }
        }
        throw new IllegalArgumentException("확인할 수 없는 학번입니다.");
    }

    private static void checkMandatoryCourses(UserTakenCoursesList inputUserTakenCourseList, Major major, ChemistryMajor chemistryMajor) {
        List<TakenCourse> mandatoryCourses = chemistryMajor.mandatoryCourses;
        UserTakenCoursesList userTakenCoursesList = major.getUserTakenCoursesList();

        List<TakenCourse> userTakenMandatoryCourses = inputUserTakenCourseList.getTakenCourses()
                .stream()
                .filter(mandatoryCourses::contains)
                .collect(Collectors.toList());
        checkException(userTakenMandatoryCourses);
        userTakenMandatoryCourses.forEach(s -> s.setCourseType(CourseType.필수));

        userTakenCoursesList.addAll(userTakenMandatoryCourses);
    }

    private static void checkException(List<TakenCourse> userTakenMandatoryCourses) {
        List<TakenCourse> physicalChemistryA = List.of(CH2102, CH3104);
        List<TakenCourse> physicalChemistryB = List.of(GS2202, CH2104);
        if (userTakenMandatoryCourses.containsAll(physicalChemistryA)) {
            userTakenMandatoryCourses.remove(CH3104);
        }
        if (userTakenMandatoryCourses.containsAll(physicalChemistryB)) {
            userTakenMandatoryCourses.remove(CH2102);
        }
    }

    private static void addLackOfMandatoryCourses(UserTakenCoursesList inputUserTakenCourseList, Major major, ChemistryMajor chemistryMajor) {
        List<TakenCourse> mandatoryCourses = chemistryMajor.mandatoryCourses;

        List<TakenCourse> lackOfMandatoryCourses = mandatoryCourses
                .stream()
                .filter(inputUserTakenCourseList::notExist)
                .collect(Collectors.toList());

        for (TakenCourse lackOfMandatoryCourse : lackOfMandatoryCourses) {
            major.addMessage(String.format("%s를 수강해야 합니다.", lackOfMandatoryCourse.toString()));
        }
    }

    private static void checkElectiveCourses(UserTakenCoursesList inputUserTakenCourseList, Major major, ChemistryMajor chemistryMajor) {
        List<TakenCourse> mandatoryCourses = chemistryMajor.mandatoryCourses;

        major.getUserTakenCoursesList().addAll(inputUserTakenCourseList.getTakenCourses()
                .stream()
                .filter(s -> !mandatoryCourses.contains(s))
                .filter(s -> s.getCourseCode().contains(CH))
                .collect(Collectors.toList()));
    }
}
