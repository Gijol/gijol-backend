package com.gist.graduation.requirment.domain.major;

import com.gist.graduation.user.taken_course.CourseType;
import com.gist.graduation.user.taken_course.TakenCourse;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static com.gist.graduation.requirment.domain.constants.MajorMandatoryConstants.Physics.*;
import static com.gist.graduation.requirment.domain.etc.Research.RESEARCH_II_CODE;
import static com.gist.graduation.requirment.domain.etc.Research.RESEARCH_I_CODE;

@RequiredArgsConstructor
public enum PhysicsMajor {

    FROM2018(List.of(18, 19, 20, 21, 22), List.of(PS2101, PS2102, PS2103, PS3103, PS3104, PS3105, PS3106, PS3107));

    private final List<Integer> studentId;
    private final List<TakenCourse> mandatoryCourses;


    public static void checkverified(UserTakenCoursesList inputUserTakenCourseList, Major major, Integer studentId) {
        for (PhysicsMajor physicsMajor : values()) {
            if (physicsMajor.studentId.contains(studentId)) {
                checkMandatoryCourses(inputUserTakenCourseList, major, physicsMajor);
                addLackOfMandatoryCourses(inputUserTakenCourseList, major, physicsMajor);
                checkElectiveCourses(inputUserTakenCourseList, major, physicsMajor);
                return;
            }
        }
        throw new IllegalArgumentException("확인할 수 없는 학번입니다.");
    }

    private static void checkMandatoryCourses(UserTakenCoursesList inputUserTakenCourseList, Major major, PhysicsMajor physicsMajor) {
        List<TakenCourse> mandatoryCourses = physicsMajor.mandatoryCourses;
        UserTakenCoursesList userTakenCoursesList = major.getUserTakenCoursesList();

        List<TakenCourse> userTakenMandatoryCourses = inputUserTakenCourseList.getTakenCourses()
                .stream()
                .filter(mandatoryCourses::contains)
                .collect(Collectors.toList());
        userTakenMandatoryCourses.forEach(s -> s.setCourseType(CourseType.필수));
        userTakenCoursesList.addAll(userTakenMandatoryCourses);
    }

    private static void addLackOfMandatoryCourses(UserTakenCoursesList inputUserTakenCourseList, Major major, PhysicsMajor physicsMajor) {
        List<TakenCourse> mandatoryCourses = physicsMajor.mandatoryCourses;

        List<TakenCourse> lackOfMandatoryCourses = mandatoryCourses
                .stream()
                .filter(inputUserTakenCourseList::notExist)
                .collect(Collectors.toList());

        for (TakenCourse lackOfMandatoryCourse : lackOfMandatoryCourses) {
            major.addMessage(String.format("%s를 수강해야 합니다.", lackOfMandatoryCourse.toString()));
        }
    }

    private static void checkElectiveCourses(UserTakenCoursesList inputUserTakenCourseList, Major major, PhysicsMajor physicsMajor) {
        List<TakenCourse> mandatoryCourses = physicsMajor.mandatoryCourses;

        major.getUserTakenCoursesList().addAll(inputUserTakenCourseList.getTakenCourses()
                .stream()
                .filter(s -> !mandatoryCourses.contains(s))
                .filter(s -> s.getCourseCode().contains(PS))
                .filter(s -> !s.getCourseCode().contains(RESEARCH_I_CODE) && !s.getCourseCode().contains(RESEARCH_II_CODE))
                .collect(Collectors.toList()));
    }
}
