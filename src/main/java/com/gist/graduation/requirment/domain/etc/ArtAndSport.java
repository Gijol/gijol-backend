package com.gist.graduation.requirment.domain.etc;

import com.gist.graduation.user.taken_course.TakenCourse;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;

import java.util.List;
import java.util.stream.Collectors;

public class ArtAndSport {

    public static final String SPORT_CODE = "GS01";
    public static final String ART_CODE = "GS02";

    public static void checkArtAndSport(EtcMandatory etcMandatory, int studentId, UserTakenCoursesList inputUserTakenCoursesList) {
        List<TakenCourse> inputUserTakenCoursesListTakenCourses = inputUserTakenCoursesList.getTakenCourses();

        List<TakenCourse> artTakenCourses = inputUserTakenCoursesListTakenCourses.stream()
                .filter(s -> s.getCourseCode().contains(ART_CODE))
                .collect(Collectors.toList());

        checkArtsMandatoryNumber(etcMandatory, artTakenCourses, studentId);

        List<TakenCourse> sportTakenCourses = inputUserTakenCoursesListTakenCourses.stream()
                .filter(s -> s.getCourseCode().contains(SPORT_CODE))
                .collect(Collectors.toList());
        checkSportMandatoryNumber(etcMandatory, sportTakenCourses, studentId);

        inputUserTakenCoursesListTakenCourses.addAll(artTakenCourses);
        inputUserTakenCoursesListTakenCourses.addAll(sportTakenCourses);
    }

    private static void checkSportMandatoryNumber(EtcMandatory etcMandatory, List<TakenCourse> takenCourses, int studentId) {
        int twoMandatory = 2;
        int fourMandatory = 4;

        int courseCount = takenCourses.size();
        if (studentId < 20 && courseCount < fourMandatory) {
            etcMandatory.addMessage(String.format("체육 과목(%s)을 %d개 더 들어야합니다.", SPORT_CODE, fourMandatory-courseCount));
        }

        if (studentId >= 20 && courseCount < twoMandatory) {
            etcMandatory.addMessage(String.format("체육 과목(%s)을 %d개 더 들어야합니다.", SPORT_CODE, twoMandatory-courseCount));
        }
    }

    private static void checkArtsMandatoryNumber(EtcMandatory etcMandatory, List<TakenCourse> takenCourses, int studentId) {
        int twoMandatory = 2;
        int fourMandatory = 4;

        int courseCount = takenCourses.size();
        if (studentId < 20 && courseCount < fourMandatory) {
            etcMandatory.addMessage(String.format("예능 과목(%s)을 %d개 더 들어야합니다.", ART_CODE, fourMandatory-courseCount));
        }

        if (studentId >= 20 && courseCount < twoMandatory) {
            etcMandatory.addMessage(String.format("예능 과목(%s)을 %d개 더 들어야합니다.", ART_CODE, twoMandatory-courseCount));
        }
    }
}
