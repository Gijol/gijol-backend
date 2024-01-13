package com.gist.graduation.requirment.domain.major;


import com.gist.graduation.course.domain.CourseInfo;
import com.gist.graduation.user.taken_course.CourseType;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;

import java.util.List;
import java.util.stream.Collectors;

import static com.gist.graduation.requirment.domain.etc.Research.RESEARCH_II_CODE;
import static com.gist.graduation.requirment.domain.etc.Research.RESEARCH_I_CODE;

public interface MajorInterface {

    default void checkMandatoryCourses(UserTakenCoursesList inputUserTakenCourseList, Major major, List<CourseInfo> mandatoryCourses) {
        UserTakenCoursesList userTakenCourses = major.getUserTakenCoursesList();

        userTakenCourses.addAll(inputUserTakenCourseList.getTakenCourses()
                .stream()
                .filter(s -> s.belongsToCourseInfosAny(mandatoryCourses))
                .map(s -> s.setCourseTypeTo(CourseType.필수))
                .collect(Collectors.toList())
        );
    }

    default void checkElectiveCourses(UserTakenCoursesList inputUserTakenCourseList, Major major, List<CourseInfo> mandatoryCourses, String majorType) {
        major.getUserTakenCoursesList().addAll(inputUserTakenCourseList.getTakenCourses()
                .stream()
                .filter(s -> !s.belongsToCourseInfosAny(mandatoryCourses))
                .filter(s -> s.getCourseCode().contains(majorType))
                .filter(s -> !s.getCourseCode().contains(RESEARCH_I_CODE) && !s.getCourseCode().contains(RESEARCH_II_CODE))
                .collect(Collectors.toList()));
    }

    default void addLackOfMandatoryCourses(Major major, List<CourseInfo> mandatoryCourses) {
        UserTakenCoursesList userTakenCourses = major.getUserTakenCoursesList();

        List<CourseInfo> lackOfMandatoryCourses = mandatoryCourses.stream()
                .filter(userTakenCourses::notExist)
                .collect(Collectors.toList());

        for (CourseInfo lackOfMandatoryCourse : lackOfMandatoryCourses) {
            major.addMessage(String.format("%s를 수강해야 합니다.", lackOfMandatoryCourse.toString()));
        }
    }

    List<CourseInfo> getMandatoryCourses();

    default boolean contains(Integer studentId){
        return studentId >= 18;
    }

}
