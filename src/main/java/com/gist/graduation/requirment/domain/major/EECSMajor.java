package com.gist.graduation.requirment.domain.major;

import com.gist.graduation.requirment.domain.Major;
import com.gist.graduation.user.taken_course.TakenCourse;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static com.gist.graduation.requirment.domain.major.MajorMandatoryConstants.EECS.*;

@RequiredArgsConstructor
public enum EECSMajor {

    FROM2018(List.of(18, 19, 20, 21, 22), 36, List.of(EC3101, EC3102));

    private final List<Integer> studentId;
    private final int totalCredit;
    private final List<TakenCourse> mandatoryCourses;


    public static void checkverified(UserTakenCoursesList inputUserTakenCourseList, Major major, Integer studentId) {
        for (EECSMajor eecsMajor : values()) {
            if (eecsMajor.studentId.contains(studentId)) {
                checkMandatoryCourses(inputUserTakenCourseList, major, eecsMajor);
                checkElectiveCourses(inputUserTakenCourseList, major, eecsMajor);
                return;
            }
        }
        throw new IllegalArgumentException("확인할 수 없는 학번입니다.");
    }

    private static void checkMandatoryCourses(UserTakenCoursesList inputUserTakenCourseList, Major major, EECSMajor eecsMajor) {
        List<TakenCourse> mandatoryCourses = eecsMajor.mandatoryCourses;
        UserTakenCoursesList userTakenCoursesList = major.getUserTakenCoursesList();

        List<TakenCourse> usertakenMandatoryCourses = inputUserTakenCourseList.getTakenCourses()
                .stream()
                .filter(mandatoryCourses::contains)
                .collect(Collectors.toList());

        userTakenCoursesList.addAll(usertakenMandatoryCourses);

        if (usertakenMandatoryCourses.isEmpty()) {
            major.addMessage(String.format("%s 중 하나를 수강해야 합니다.", mandatoryCourses));
        }
    }

    private static void checkElectiveCourses(UserTakenCoursesList inputUserTakenCourseList, Major major, EECSMajor eecsMajor) {
        List<TakenCourse> mandatoryCourses = eecsMajor.mandatoryCourses;

        major.getUserTakenCoursesList().addAll(inputUserTakenCourseList.getTakenCourses()
                .stream()
                .filter(s -> !mandatoryCourses.contains(s))
                .filter(s -> s.getCourseCode().contains(EC))
                .collect(Collectors.toList()));
    }
}
