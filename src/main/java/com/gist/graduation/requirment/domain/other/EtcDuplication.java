package com.gist.graduation.requirment.domain.other;

import com.gist.graduation.user.taken_course.TakenCourse;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;

import java.util.List;
import java.util.stream.Collectors;

public class EtcDuplication {

    public static final TakenCourse SOCIAL_VOLUNTEER = new TakenCourse("사회봉사", "UC0201", "1");
    public static final TakenCourse OVER_SEAS_VOLUNTEER = new TakenCourse("해외봉사", "UC0203", "1");
    public static final TakenCourse CREATIVITY = new TakenCourse("창의함양", "UC0202", "1");

    public static void checkDuplicate(UserTakenCoursesList inputUserTakenCoursesList) {
        if (inputUserTakenCoursesList.getTakenCourses().containsAll(List.of(SOCIAL_VOLUNTEER, OVER_SEAS_VOLUNTEER))) {
            inputUserTakenCoursesList.getTakenCourses().remove(SOCIAL_VOLUNTEER);
        }

        List<TakenCourse> creativityCourses = inputUserTakenCoursesList.getTakenCourses().stream().filter(s -> s.equals(CREATIVITY)).collect(Collectors.toList());
        if (creativityCourses.size() > 1) {
            inputUserTakenCoursesList.getTakenCourses().removeAll(creativityCourses);
            inputUserTakenCoursesList.getTakenCourses().add(creativityCourses.get(0));
        }
    }
}
