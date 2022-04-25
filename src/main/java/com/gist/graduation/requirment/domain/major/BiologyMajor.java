package com.gist.graduation.requirment.domain.major;

import com.gist.graduation.requirment.domain.Major;
import com.gist.graduation.user.taken_course.TakenCourse;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static com.gist.graduation.requirment.domain.major.MajorMandatoryConstants.Biology.*;
import static com.gist.graduation.requirment.domain.major.MajorMandatoryConstants.Chemistry.*;

@RequiredArgsConstructor
public enum BiologyMajor {

    FROM2018(List.of(18), 36, List.of(BS3101, BS3105, BS3111, BS3112, BS3113)),
    FROM2019(List.of(19, 20), 36, List.of(BS2101, BS2102, BS3101, BS3105, BS3111, BS3112, BS3113)),
    FROM2021(List.of(21), 36, List.of(BS2101, BS2102, BS2103, BS2104, BS3101, BS3105, BS3112));


    private final List<Integer> studentId;
    private final int totalCredit;
    private final List<TakenCourse> mandatoryCourses;


    public static void checkverified(UserTakenCoursesList inputUserTakenCourseList, Major major, Integer studentId) {
        for (BiologyMajor biologyMajor : values()) {
            if (biologyMajor.studentId.contains(studentId)) {
                checkMandatoryCourses(inputUserTakenCourseList, major, biologyMajor);
                addLackOfMandatoryCourses(inputUserTakenCourseList, major, biologyMajor);
                checkElectiveCourses(inputUserTakenCourseList, major, biologyMajor);
                return;
            }
        }
        throw new IllegalArgumentException("확인할 수 없는 학번입니다.");
    }

    private static void checkMandatoryCourses(UserTakenCoursesList inputUserTakenCourseList, Major major, BiologyMajor biologyMajor) {
        List<TakenCourse> mandatoryCourses = biologyMajor.mandatoryCourses;
        UserTakenCoursesList userTakenCoursesList = major.getUserTakenCoursesList();

        List<TakenCourse> userTakenMandatoryCourses = inputUserTakenCourseList.getTakenCourses()
                .stream()
                .filter(mandatoryCourses::contains)
                .collect(Collectors.toList());

        userTakenCoursesList.addAll(userTakenMandatoryCourses);
    }

    private static void addLackOfMandatoryCourses(UserTakenCoursesList inputUserTakenCourseList, Major major, BiologyMajor biologyMajor) {
        List<TakenCourse> mandatoryCourses = biologyMajor.mandatoryCourses;

        List<TakenCourse> lackOfMandatoryCourses = mandatoryCourses
                .stream()
                .filter(inputUserTakenCourseList::notExist)
                .collect(Collectors.toList());

        for (TakenCourse lackOfMandatoryCourse : lackOfMandatoryCourses) {
            major.addMessage(String.format("%s를 수강해야 합니다.", lackOfMandatoryCourse.toString()));
        }
    }

    private static void checkElectiveCourses(UserTakenCoursesList inputUserTakenCourseList, Major major, BiologyMajor biologyMajor) {
        List<TakenCourse> mandatoryCourses = biologyMajor.mandatoryCourses;

        major.getUserTakenCoursesList().addAll(inputUserTakenCourseList.getTakenCourses()
                .stream()
                .filter(s -> !mandatoryCourses.contains(s))
                .filter(s -> s.getCourseCode().contains(BS))
                .collect(Collectors.toList()));
    }
}
