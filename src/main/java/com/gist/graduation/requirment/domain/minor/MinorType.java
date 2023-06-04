package com.gist.graduation.requirment.domain.minor;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.gist.graduation.config.exception.ApplicationException;
import com.gist.graduation.course.domain.CourseInfo;
import com.gist.graduation.user.taken_course.CourseType;
import com.gist.graduation.user.taken_course.TakenCourse;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;

import java.util.List;
import java.util.stream.Collectors;

import static com.gist.graduation.requirment.domain.etc.Research.RESEARCH_II_CODE;
import static com.gist.graduation.requirment.domain.etc.Research.RESEARCH_I_CODE;

public enum MinorType {
    EC,
    MA,
    MC,
    EV,
    BS,
    PS,
    CH,
    MM,
    CT,
    LH,
    EB,
    MB,
    SS,
    PP,
    MD,
    FE,
    IR,
    AI,
    NONE;

    private static final List<CourseInfo> mandatoryCourse = List.of(
            new CourseInfo("미분방정식과 응용", "GS2002", "3"),
            new CourseInfo("다변수해석학과 응용", "GS2001", "3"),
            new CourseInfo("선형대수학과 응용", "GS2004", "3"),
            new CourseInfo("현대대수학", "MM4004", "3")
    );

    public void checkMathMinor(Integer studentId, Minor minor, UserTakenCoursesList inputUserTakenCoursesList) {
        UserTakenCoursesList userTakenCourses = minor.getUserTakenCoursesList();

        userTakenCourses.addAll(inputUserTakenCoursesList.getTakenCourses()
                .stream()
                .filter(s -> s.belongsToCourseInfosAny(mandatoryCourse))
                .map(s -> s.setCourseTypeTo(CourseType.필수))
                .collect(Collectors.toList()));

        List<CourseInfo> lackOfCourses = mandatoryCourse.stream()
                .filter(userTakenCourses::notExist)
                .collect(Collectors.toList());

        for (CourseInfo courseInfo : lackOfCourses) {
            minor.addMessage(String.format("%s를 수강해야 합니다.", courseInfo.toString()));
        }

        List<TakenCourse> electiveCourses = inputUserTakenCoursesList.getTakenCourses()
                .stream()
                .filter(s -> !s.belongsToCourseInfosAny(mandatoryCourse))
                .filter(s -> s.getCourseCode().contains("MM"))
                .filter(s -> !s.getCourseCode().contains(RESEARCH_I_CODE) && !s.getCourseCode().contains(RESEARCH_II_CODE))
                .collect(Collectors.toList());

        minor.getUserTakenCoursesList().addAll(electiveCourses);

        if(minor.getMessages().isEmpty()){
            if (studentId <= 20) {
                Integer lackCredit = 15 - userTakenCourses.sumCreditOfCourses();
                if (lackCredit > 0){
                    minor.addMessage(String.format("부전공 과목을 %s학점 더 들어야 합니다.", lackCredit));
                }
            }
            if (studentId > 21) {
                Integer lackCredit = 18 - minor.getTotalCredits();
                if (lackCredit > 0){
                    minor.addMessage(String.format("부전공 과목을 %s학점 더 들어야 합니다.", lackCredit));
                }
            }
        }
    }

    @JsonCreator
    public static MinorType fromMajorType(String minor) {
        for (MinorType minorType : MinorType.values()) {
            if (minorType.name().equals(minor)) {
                return minorType;
            }
        }
        throw new ApplicationException("존재하지 않는 전공입니다.");
    }
}
