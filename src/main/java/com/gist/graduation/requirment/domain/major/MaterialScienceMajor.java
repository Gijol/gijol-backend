package com.gist.graduation.requirment.domain.major;

import com.gist.graduation.user.taken_course.CourseType;
import com.gist.graduation.user.taken_course.TakenCourse;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static com.gist.graduation.requirment.domain.constants.MajorMandatoryConstants.MaterialScience.*;

@RequiredArgsConstructor
public enum MaterialScienceMajor {

    FROM2018(List.of(18, 19, 20, 21, 22), List.of(MA2101, MA2102, MA2103, MA2104, MA3104, MA3105));

    private final List<Integer> studentId;
    private final List<TakenCourse> mandatoryCourses;

    public static void checkverified(UserTakenCoursesList inputUserTakenCourseList, Major major, Integer studentId) {
        for (MaterialScienceMajor materialScienceMajor : values()) {
            if (materialScienceMajor.studentId.contains(studentId)) {
                checkMandatoryCourses(inputUserTakenCourseList, major, materialScienceMajor);
                addLackOfMandatoryCourses(inputUserTakenCourseList, major, materialScienceMajor);
                checkElectiveCourses(inputUserTakenCourseList, major, materialScienceMajor);
                return;
            }
        }
        throw new IllegalArgumentException("확인할 수 없는 학번입니다.");
    }

    private static void checkMandatoryCourses(UserTakenCoursesList inputUserTakenCourseList, Major major, MaterialScienceMajor materialScienceMajor) {
        List<TakenCourse> mandatoryCourses = materialScienceMajor.mandatoryCourses;
        UserTakenCoursesList userTakenCoursesList = major.getUserTakenCoursesList();

        List<TakenCourse> userTakenMandatoryCourses = inputUserTakenCourseList.getTakenCourses()
                .stream()
                .filter(mandatoryCourses::contains)
                .collect(Collectors.toList());
        userTakenMandatoryCourses.forEach(s -> s.setCourseType(CourseType.필수));
        userTakenCoursesList.addAll(userTakenMandatoryCourses);
    }

    private static void addLackOfMandatoryCourses(UserTakenCoursesList inputUserTakenCourseList, Major major, MaterialScienceMajor materialScienceMajor) {
        List<TakenCourse> mandatoryCourses = materialScienceMajor.mandatoryCourses;

        List<TakenCourse> lackOfMandatoryCourses = mandatoryCourses
                .stream()
                .filter(inputUserTakenCourseList::notExist)
                .collect(Collectors.toList());

        for (TakenCourse lackOfMandatoryCourse : lackOfMandatoryCourses) {
            major.addMessage(String.format("%s를 수강해야 합니다.", lackOfMandatoryCourse.toString()));
        }
    }

    private static void checkElectiveCourses(UserTakenCoursesList inputUserTakenCourseList, Major major, MaterialScienceMajor materialScienceMajor) {
        List<TakenCourse> mandatoryCourses = materialScienceMajor.mandatoryCourses;

        major.getUserTakenCoursesList().addAll(inputUserTakenCourseList.getTakenCourses()
                .stream()
                .filter(s -> !mandatoryCourses.contains(s))
                .filter(s -> s.getCourseCode().contains(MA))
                .collect(Collectors.toList()));
    }
}
