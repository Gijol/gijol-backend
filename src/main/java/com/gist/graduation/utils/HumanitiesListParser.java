package com.gist.graduation.utils;

import com.gist.graduation.requirment.domain.constants.HumanitiesExceptionConstants;
import com.gist.graduation.user.taken_course.TakenCourse;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class HumanitiesListParser {

    private static final List<Integer> HUS_COURSE_CODE_LIST = List.of(25, 26, 28, 35, 36, 38, 39);
    private static final List<Integer> PPE_COURSE_CODE_LIST = List.of(26, 27, 28, 36, 37, 38, 47);

    public static List<TakenCourse> getHUSCoursesList() {
        List<TakenCourse> HUSCoursesList = getHumanitiesCoursesList().stream()
                .filter(s -> HUS_COURSE_CODE_LIST.stream().anyMatch(t -> s.getCourseCode().contains(t.toString())))
                .collect(Collectors.toList());

        HumanitiesExceptionConstants.HUSAmbiguousHumanities.removeNotHUS(HUSCoursesList);
        return HUSCoursesList;
    }

    public static List<TakenCourse> getPPECoursesList() {
        List<TakenCourse> PPECoursesList = getHumanitiesCoursesList().stream()
                .filter(s -> PPE_COURSE_CODE_LIST.stream().anyMatch(t -> s.getCourseCode().contains(t.toString())))
                .collect(Collectors.toList());

        HumanitiesExceptionConstants.PPEAmbiguousHumanities.removeNotPPE(PPECoursesList);
        return PPECoursesList;
    }

    public static List<TakenCourse> getHumanitiesCoursesList() {
        List<RegisteredCourse> undergradCourses = CourseListParser.getCourseList();
        List<String> humanitiesCodeList = new ArrayList<>();
        addHumanitiesCode(humanitiesCodeList);

        Set<RegisteredCourse> conditionCourse = new HashSet<>();

        for (String code : humanitiesCodeList) {
            addRegisteredCoursesByCode(undergradCourses, conditionCourse, code);
        }

        List<TakenCourse> conditionCourseList = TakenCourse.setToListOf(conditionCourse);
        HumanitiesExceptionConstants.NotHumanities.removeHumanitiesException(conditionCourseList);

        return conditionCourseList;
    }

    private static void addRegisteredCoursesByCode(List<RegisteredCourse> undergradCourses, Set<RegisteredCourse> conditionCourse, String code) {
        conditionCourse.addAll(undergradCourses.stream()
                .filter(s -> s.getCode().contains(code))
                .collect(Collectors.toSet()));
    }

    private static void addHumanitiesCode(List<String> humanitiesCodeList) {
        for (int i = 2; i <= 4; i++) {
            for (int j = 5; j <= 9; j++) {
                Integer num = 10 * i + j;
                humanitiesCodeList.add(("GS" + num));
            }
        }
    }


}
