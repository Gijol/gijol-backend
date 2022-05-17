package com.gist.graduation.requirment.domain.etc;

import com.gist.graduation.requirment.domain.major.MajorType;
import com.gist.graduation.user.taken_course.TakenCourse;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;

import java.util.List;
import java.util.stream.Collectors;

public class Research {

    public static final String RESEARCH_I_CODE = "9102";
    public static final String RESEARCH_II_CODE = "9103";

    public static void checkResearch(EtcMandatory etcMandatory, UserTakenCoursesList inputUserTakenCoursesList, MajorType majorType) {
        List<TakenCourse> inputUserTakenCoursesListTakenCourses = inputUserTakenCoursesList.getTakenCourses();

        List<TakenCourse> researchOneCourses = inputUserTakenCoursesListTakenCourses.stream()
                .filter(s -> s.getCourseCode().contains(RESEARCH_I_CODE))
                .collect(Collectors.toList());
        checkExist(etcMandatory, researchOneCourses, RESEARCH_I_CODE, majorType.name());


        List<TakenCourse> researchTwoCourses = inputUserTakenCoursesListTakenCourses.stream()
                .filter(s -> s.getCourseCode().contains(RESEARCH_II_CODE))
                .collect(Collectors.toList());
        checkExist(etcMandatory, researchTwoCourses, RESEARCH_II_CODE, majorType.name());

        etcMandatory.getUserTakenCoursesList().addAll(researchOneCourses);
        etcMandatory.getUserTakenCoursesList().addAll(researchTwoCourses);
    }

    private static void checkExist(EtcMandatory etcMandatory, List<TakenCourse> takenCourses, String code, String majorType) {
        if (takenCourses.isEmpty() && code.equals(RESEARCH_I_CODE)) {
            etcMandatory.addMessage(String.format("학사논문연구 I(%s) 과목을 수강해야 합니다.", majorType + code));
            return;
        }

        if (takenCourses.isEmpty() && code.equals(RESEARCH_II_CODE)) {
            etcMandatory.addMessage(String.format("학사논문연구 II(%s) 과목을 수강해야 합니다.", majorType + code));
        }

    }
}
