package com.gist.graduation.requirment.domain.major;

import com.gist.graduation.user.taken_course.CourseType;
import com.gist.graduation.user.taken_course.TakenCourse;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static com.gist.graduation.requirment.domain.constants.MajorMandatoryConstants.MechanicalEngineering.*;
import static com.gist.graduation.requirment.domain.etc.Research.RESEARCH_II_CODE;
import static com.gist.graduation.requirment.domain.etc.Research.RESEARCH_I_CODE;

@RequiredArgsConstructor
public enum MechanicalEngineeringMajor {

    FROM2018(List.of(18, 19, 20, 21, 22), List.of(MC2100, MC2100_1, MC2101, MC2101_1, MC2102, MC2102_1, MC2103, MC3106, MC3107));

    private final List<Integer> studentId;
    private final List<TakenCourse> mandatoryCourses;


    public static void checkverified(UserTakenCoursesList inputUserTakenCourseList, Major major, Integer studentId) {
        for (MechanicalEngineeringMajor mechanicalEngineeringMajor : values()) {
            if (mechanicalEngineeringMajor.studentId.contains(studentId)) {
                checkMandatoryCourses(inputUserTakenCourseList, major, mechanicalEngineeringMajor);
                addLackOfMandatoryCourses(inputUserTakenCourseList, major, mechanicalEngineeringMajor);
                checkElectiveCourses(inputUserTakenCourseList, major, mechanicalEngineeringMajor);
                return;
            }
        }
        throw new IllegalArgumentException("확인할 수 없는 학번입니다.");
    }

    private static void checkMandatoryCourses(UserTakenCoursesList inputUserTakenCourseList, Major major, MechanicalEngineeringMajor mechanicalEngineeringMajor) {
        List<TakenCourse> mandatoryCourses = mechanicalEngineeringMajor.mandatoryCourses;
        UserTakenCoursesList userTakenCoursesList = major.getUserTakenCoursesList();

        List<TakenCourse> userTakenMandatoryCourses = inputUserTakenCourseList.getTakenCourses()
                .stream()
                .filter(mandatoryCourses::contains)
                .collect(Collectors.toList());
        userTakenMandatoryCourses.forEach(s -> s.setCourseType(CourseType.필수));
        userTakenCoursesList.addAll(userTakenMandatoryCourses);
    }

    private static void addLackOfMandatoryCourses(UserTakenCoursesList inputUserTakenCourseList, Major major, MechanicalEngineeringMajor mechanicalEngineeringMajor) {
        List<TakenCourse> mandatoryCourses = mechanicalEngineeringMajor.mandatoryCourses;

        List<TakenCourse> lackOfMandatoryCourses = mandatoryCourses
                .stream()
                .filter(inputUserTakenCourseList::notExist)
                .collect(Collectors.toList());

        removeDuplication(lackOfMandatoryCourses);

        for (TakenCourse lackOfMandatoryCourse : lackOfMandatoryCourses) {
            major.addMessage(String.format("%s를 수강해야 합니다.", lackOfMandatoryCourse.toString()));
        }
    }

    private static void removeDuplication(List<TakenCourse> lackOfMandatoryClass) {
        List<TakenCourse> thermodynamics = List.of(MC2100, MC2100_1);
        List<TakenCourse> solidMechanics = List.of(MC2101, MC2101_1);
        List<TakenCourse> fluidMechanics = List.of(MC2102, MC2102_1);

        removeDuplicate(lackOfMandatoryClass, thermodynamics, MC2100);
        removeDuplicate(lackOfMandatoryClass, solidMechanics, MC2101);
        removeDuplicate(lackOfMandatoryClass, fluidMechanics, MC2102);
    }

    private static void removeDuplicate(List<TakenCourse> userTakenMandatoryCourses, List<TakenCourse> duplicatedNameCourse, TakenCourse oldCourse) {
        if (userTakenMandatoryCourses.stream().anyMatch(duplicatedNameCourse::contains)) {
            if (userTakenMandatoryCourses.containsAll(duplicatedNameCourse)) {
                userTakenMandatoryCourses.remove(oldCourse);
                return;
            }
            userTakenMandatoryCourses.removeAll(duplicatedNameCourse);
        }
    }

    private static void checkElectiveCourses(UserTakenCoursesList inputUserTakenCourseList, Major major, MechanicalEngineeringMajor mechanicalEngineeringMajor) {
        List<TakenCourse> mandatoryCourses = mechanicalEngineeringMajor.mandatoryCourses;

        major.getUserTakenCoursesList().addAll(inputUserTakenCourseList.getTakenCourses()
                .stream()
                .filter(s -> !mandatoryCourses.contains(s))
                .filter(s -> s.getCourseCode().contains(MC))
                .filter(s -> !s.getCourseCode().contains(RESEARCH_I_CODE) && !s.getCourseCode().contains(RESEARCH_II_CODE))
                .collect(Collectors.toList()));
    }
}
