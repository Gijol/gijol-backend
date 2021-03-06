package com.gist.graduation.requirment.domain.major;

import com.gist.graduation.requirment.domain.etc.Research;
import com.gist.graduation.user.taken_course.CourseType;
import com.gist.graduation.user.taken_course.TakenCourse;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static com.gist.graduation.requirment.domain.constants.MajorMandatoryConstants.EECS.*;
import static com.gist.graduation.requirment.domain.etc.Research.RESEARCH_II_CODE;
import static com.gist.graduation.requirment.domain.etc.Research.RESEARCH_I_CODE;

@RequiredArgsConstructor
public enum EECSMajor {

    FROM2018(List.of(18, 19, 20, 21, 22), List.of(EC3101, EC3102));

    private final List<Integer> studentId;
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

        List<TakenCourse> userTakenMandatoryCourses = inputUserTakenCourseList.getTakenCourses()
                .stream()
                .filter(mandatoryCourses::contains)
                .collect(Collectors.toList());
        userTakenMandatoryCourses.forEach(s -> s.setCourseType(CourseType.필수));
        userTakenCoursesList.addAll(userTakenMandatoryCourses);

        if (userTakenMandatoryCourses.isEmpty()) {
            major.addMessage(String.format("%s 혹은 %s 수강해야 합니다.", mandatoryCourses.get(0),mandatoryCourses.get(1)));
        }
    }

    private static void checkElectiveCourses(UserTakenCoursesList inputUserTakenCourseList, Major major, EECSMajor eecsMajor) {
        List<TakenCourse> mandatoryCourses = eecsMajor.mandatoryCourses;

        major.getUserTakenCoursesList().addAll(inputUserTakenCourseList.getTakenCourses()
                .stream()
                .filter(s -> !mandatoryCourses.contains(s))
                .filter(s -> s.getCourseCode().contains(EC))
                .filter(s -> !s.getCourseCode().contains(RESEARCH_I_CODE) && !s.getCourseCode().contains(RESEARCH_II_CODE))
                .collect(Collectors.toList()));
    }
}
