package com.gist.graduation.requirment.domain.major;

import com.gist.graduation.requirment.domain.Major;
import com.gist.graduation.user.taken_course.TakenCourse;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static com.gist.graduation.requirment.domain.major.MajorMandatoryConstants.Chemistry.*;
import static com.gist.graduation.requirment.domain.major.MajorMandatoryConstants.MechanicalEngineering.*;

@RequiredArgsConstructor
public enum MechanicalEngineeringMajor {

    FROM2018(List.of(18), 36, List.of(MC3103, MC3105, MC4101)),
    FROM2019(List.of(19), 36, List.of(MC2100, MC2100_1, MC2101, MC2101_1, MC3103, MC3105)),
    FROM2020(List.of(20), 36, List.of(MC2100, MC2100_1, MC2101, MC2101_1, MC2102, MC2102_1, MC2103, MC3212)),
    FROM2021(List.of(21), 36, List.of(MC2100, MC2100_1, MC2101, MC2101_1, MC2102, MC2102_1, MC2103, MC3106, MC3107));

    private final List<Integer> studentId;
    private final int totalCredit;
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
        checkException(userTakenMandatoryCourses);

        userTakenCoursesList.addAll(userTakenMandatoryCourses);
    }

    private static void checkException(List<TakenCourse> userTakenMandatoryCourses) {
        // todo add MC exception
//        List<TakenCourse> physicalChemistryA = List.of(CH2102, CH3104);
//        if (userTakenMandatoryCourses.containsAll(physicalChemistryA)){
//            userTakenMandatoryCourses.remove(CH3104);
//        }
    }

    private static void addLackOfMandatoryCourses(UserTakenCoursesList inputUserTakenCourseList, Major major, MechanicalEngineeringMajor mechanicalEngineeringMajor) {
        List<TakenCourse> mandatoryCourses = mechanicalEngineeringMajor.mandatoryCourses;

        List<TakenCourse> lackOfMandatoryCourses = mandatoryCourses
                .stream()
                .filter(inputUserTakenCourseList::notExist)
                .collect(Collectors.toList());

        for (TakenCourse lackOfMandatoryCourse : lackOfMandatoryCourses) {
            major.addMessage(String.format("%s를 수강해야 합니다.", lackOfMandatoryCourse.toString()));
        }
    }

    private static void checkElectiveCourses(UserTakenCoursesList inputUserTakenCourseList, Major major, MechanicalEngineeringMajor mechanicalEngineeringMajor) {
        List<TakenCourse> mandatoryCourses = mechanicalEngineeringMajor.mandatoryCourses;

        major.getUserTakenCoursesList().addAll(inputUserTakenCourseList.getTakenCourses()
                .stream()
                .filter(s -> !mandatoryCourses.contains(s))
                .filter(s -> s.getCourseCode().contains(CH))
                .collect(Collectors.toList()));
    }
}
