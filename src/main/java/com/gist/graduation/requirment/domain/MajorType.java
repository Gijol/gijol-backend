package com.gist.graduation.requirment.domain;

import com.gist.graduation.user.taken_course.TakenCourse;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;
import com.gist.graduation.utils.CourseListParser;
import org.apache.poi.ss.formula.functions.T;
import org.thymeleaf.util.ListUtils;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public enum MajorType {
    EC(36,
            List.of(new TakenCourse("전자공학실험", "EC3101", "3"),
                    new TakenCourse("컴퓨터 시스템 이론 및 실험", "EC3102", "4")
            )),
    MA(30,
            List.of(new TakenCourse("재료과학", "MA2101", "3"),
                    new TakenCourse("유기재료실험", "MA3105", "3"),
                    new TakenCourse("열역학", "MA2102", "3"),
                    new TakenCourse("고분자과학", "MA2104", "3"),
                    new TakenCourse("유기재료화학", "MA2103", "3"),
                    new TakenCourse("전자재료실험", "MA3104", "3")
            )),
    MC(36,
            List.of(new TakenCourse("열역학", "MC2100", "3"),
                    new TakenCourse("열역학 I", "MC2100", "3"),
                    new TakenCourse("고체역학", "MC2101", "3"),
                    new TakenCourse("고체역학 I", "MC2101", "3"),
                    new TakenCourse("유체역학", "MC2102", "3"),
                    new TakenCourse("유체역학 I", "MC2102", "3"),
                    new TakenCourse("유체역학", "MC3105", "3"),
                    new TakenCourse("동역학", "MC2103", "3"),
                    new TakenCourse("기계공학실험Ⅱ", "MC3107", "3"),
                    new TakenCourse("기계공학실험Ⅰ", "MC3106", "3"),
                    new TakenCourse("기계공학실험Ⅰ", "MC3212", "3")
            )),
    EV(36,
            List.of(new TakenCourse("지구환경이동현상", "EV4106", "3"),
                    new TakenCourse("지구시스템과학", "EV3111", "3"),
                    new TakenCourse("환경공학", "EV3101", "3"),
                    new TakenCourse("환경분석실험 II", "EV4107", "3"),
                    new TakenCourse("환경분석실험 I", "EV3106", "3")
            )),
    BS(36,
            List.of(new TakenCourse("세포생물학", "BS3105", "3"),
                    new TakenCourse("유기화학 Ⅰ", "BS2101", "3"),
                    new TakenCourse("분자생물학", "BS2102", "3"),
                    new TakenCourse("생화학 II", "BS3101", "3"),
                    new TakenCourse("생화학 I", "BS2104", "3"),
                    new TakenCourse("생화학 I", "BS3113", "3"),
                    new TakenCourse("세포·발생생물학 실험", "BS3112", "3"),
                    new TakenCourse("생화학·분자생물학 실험", "BS3111", "3"),
                    new TakenCourse("생화학·분자생물학 실험", "BS2103", "3")
            )),
    PS(36,
            List.of(new TakenCourse("양자물리 및 연습 I", "PS3103", "3"),
                    new TakenCourse("열역학 및 통계물리", "PS3105", "3"),
                    new TakenCourse("전자기학 및 연습 Ⅰ", "PS2102", "3"),
                    new TakenCourse("전자기학 및 연습Ⅱ", "PS2103", "3"),
                    new TakenCourse("고전역학 및 연습Ⅰ", "PS2101", "3"),
                    new TakenCourse("양자물리 및 연습 II", "PS3104", "3"),
                    new TakenCourse("수리물리 I", "PS3107", "3"),
                    new TakenCourse("물리실험 I", "PS3106", "3")
            )),
    CH(36,
            List.of(new TakenCourse("분석화학", "CH2101", "3"),
                    new TakenCourse("물리화학A", "CH2102", "3"),
                    new TakenCourse("유기화학Ⅰ", "CH2103", "3"),
                    new TakenCourse("물리화학 B", "CH2104", "3"),
                    new TakenCourse("물리화학 II", "CH3104", "3"),
                    new TakenCourse("화학합성실험", "CH2105", "3"),
                    new TakenCourse("생화학 Ⅰ", "CH3106", "3"),
                    new TakenCourse("무기화학", "CH3107", "3")
                    ));

    private int TotalMajorCredits;
    private final List<TakenCourse> mandatoryMajorCourse;

    MajorType(int totalMajorCredits, List<TakenCourse> mandatoryMajorCourse) {
        this.TotalMajorCredits = totalMajorCredits;
        this.mandatoryMajorCourse = mandatoryMajorCourse;
    }

    public int getTotalMajorCredits() {
        return TotalMajorCredits;
    }

    public List<TakenCourse> getMandatoryMajorCourse() {
        return mandatoryMajorCourse;
    }

    public static List<TakenCourse> getUserTakenMajorCourses(UserTakenCoursesList inputUserTakenCoursesList, MajorType majorType) {
        return inputUserTakenCoursesList.getTakenCourses()
                .stream()
                .filter(majorType.mandatoryMajorCourse::contains)
                .collect(Collectors.toList());

    }

    public static List<TakenCourse> getLackMajorMandatoryCourse(UserTakenCoursesList inputUserTakenCoursesList, MajorType majorType) {
        return majorType.mandatoryMajorCourse
                .stream()
                .filter(inputUserTakenCoursesList::notExist)
                .collect(Collectors.toList());
    }


}
