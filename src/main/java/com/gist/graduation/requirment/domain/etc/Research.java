package com.gist.graduation.requirment.domain.etc;

import com.gist.graduation.user.taken_course.TakenCourse;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;

import java.util.List;
import java.util.stream.Collectors;

public class Research {

    public static final String RESEARCH_I_CODE = "9102";
    public static final String RESEARCH_II_CODE = "9103";

    public static void checkResearch(EtcMandatory etcMandatory, UserTakenCoursesList inputUserTakenCoursesList) {
        List<TakenCourse> inputUserTakenCoursesListTakenCourses = inputUserTakenCoursesList.getTakenCourses();

        List<TakenCourse> researchOneCourses = inputUserTakenCoursesListTakenCourses.stream()
                .filter(s -> s.getCourseCode().contains(RESEARCH_I_CODE))
                .collect(Collectors.toList());
        checkExist(etcMandatory, researchOneCourses, RESEARCH_I_CODE);


        List<TakenCourse> researchTwoCourses = inputUserTakenCoursesListTakenCourses.stream()
                .filter(s -> s.getCourseCode().contains(RESEARCH_II_CODE))
                .collect(Collectors.toList());
        checkExist(etcMandatory, researchTwoCourses, RESEARCH_II_CODE);

        inputUserTakenCoursesListTakenCourses.addAll(researchOneCourses);
        inputUserTakenCoursesListTakenCourses.addAll(researchTwoCourses);
    }

    private static void checkExist(EtcMandatory etcMandatory, List<TakenCourse> takenCourses, String code) {
        if (takenCourses.isEmpty() && code.equals(RESEARCH_I_CODE)) {
            etcMandatory.addMessage("학사논문연구 I 과목을 수강해야 합니다.");
            return;
        }

        if (takenCourses.isEmpty() && code.equals(RESEARCH_II_CODE)) {
            etcMandatory.addMessage("학사논문연구 II 과목을 수강해야 합니다.");
        }

    }
}