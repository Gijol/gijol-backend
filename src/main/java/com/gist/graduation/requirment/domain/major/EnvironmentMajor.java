package com.gist.graduation.requirment.domain.major;

import com.gist.graduation.requirment.domain.Major;
import com.gist.graduation.user.taken_course.TakenCourse;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static com.gist.graduation.requirment.domain.major.MajorMandatoryConstants.Biology.*;
import static com.gist.graduation.requirment.domain.major.MajorMandatoryConstants.Environment.*;

@RequiredArgsConstructor
public enum EnvironmentMajor {

    FROM2018(List.of(18), 36, List.of(EV3101, EV3104, EV3105, EV3106, EV4105, EV4106)),
    FROM2019(List.of(19), 36, List.of(EV3101, EV3106, EV4105, EV4107)),
    FROM2020(List.of(20, 21), 36, List.of(EV3101, EV3106, EV3111, EV4106, EV4107));

    private final List<Integer> studentId;
    private final int totalCredit;
    private final List<TakenCourse> mandatoryCourses;

    public static void checkverified(UserTakenCoursesList inputUserTakenCourseList, Major major, Integer studentId) {
        for (EnvironmentMajor environmentMajor : values()) {
            if (environmentMajor.studentId.contains(studentId)) {
                checkMandatoryCourses(inputUserTakenCourseList, major, environmentMajor);
                addLackOfMandatoryCourses(inputUserTakenCourseList, major, environmentMajor);
                checkElectiveCourses(inputUserTakenCourseList, major, environmentMajor);
                return;
            }
        }
        throw new IllegalArgumentException("확인할 수 없는 학번입니다.");
    }

    private static void checkMandatoryCourses(UserTakenCoursesList inputUserTakenCourseList, Major major, EnvironmentMajor environmentMajor) {
        List<TakenCourse> mandatoryCourses = environmentMajor.mandatoryCourses;
        UserTakenCoursesList userTakenCoursesList = major.getUserTakenCoursesList();

        List<TakenCourse> userTakenMandatoryCourses = inputUserTakenCourseList.getTakenCourses()
                .stream()
                .filter(mandatoryCourses::contains)
                .collect(Collectors.toList());

        userTakenCoursesList.addAll(userTakenMandatoryCourses);
    }

    private static void addLackOfMandatoryCourses(UserTakenCoursesList inputUserTakenCourseList, Major major, EnvironmentMajor environmentMajor) {
        List<TakenCourse> mandatoryCourses = environmentMajor.mandatoryCourses;

        List<TakenCourse> lackOfMandatoryCourses = mandatoryCourses
                .stream()
                .filter(inputUserTakenCourseList::notExist)
                .collect(Collectors.toList());

        for (TakenCourse lackOfMandatoryCourse : lackOfMandatoryCourses) {
            major.addMessage(String.format("%s를 수강해야 합니다.", lackOfMandatoryCourse.toString()));
        }
    }

    private static void checkElectiveCourses(UserTakenCoursesList inputUserTakenCourseList, Major major, EnvironmentMajor environmentMajor) {
        List<TakenCourse> mandatoryCourses = environmentMajor.mandatoryCourses;

        major.getUserTakenCoursesList().addAll(inputUserTakenCourseList.getTakenCourses()
                .stream()
                .filter(s -> !mandatoryCourses.contains(s))
                .filter(s -> s.getCourseCode().contains(BS))
                .collect(Collectors.toList()));
    }
}
