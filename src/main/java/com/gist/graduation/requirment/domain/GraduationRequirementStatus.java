package com.gist.graduation.requirment.domain;

import com.gist.graduation.requirment.domain.major.MajorType;
import com.gist.graduation.requirment.domain.other.EtcDuplication;
import com.gist.graduation.user.taken_course.TakenCourse;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString
@RequiredArgsConstructor
public class GraduationRequirementStatus {

    public static final int TOTAL_CREDIT_CONDITION = 130;

    private final GraduationCategory graduationCategory;
    private Integer totalCredits;
    private Boolean totalSatisfied;


    public GraduationRequirementStatus() {
        this.graduationCategory = new GraduationCategory();
    }

    public void checkGraduationRequirements(Integer studentId, UserTakenCoursesList userTakenCoursesList, MajorType majorType) {
        EtcDuplication.checkDuplicate(userTakenCoursesList);
        setTotalCredits(userTakenCoursesList);
        this.totalSatisfied = graduationCategory.checkSatisfied(this);

        values().forEach(s -> s.checkRequirementByStudentId(studentId, userTakenCoursesList, majorType));
        this.getGraduationCategory().getOtherUncheckedClass().checkRequirementByStudentId(studentId, userTakenCoursesList, this);
    }

    public List<TakenCourse> listOfExceptOtherUncheckedClasses() {
        return values().stream()
                .flatMap(s -> s.getUserTakenCoursesList().getTakenCourses().stream()).collect(Collectors.toList());
    }

    private void setTotalCredits(UserTakenCoursesList userTakenCoursesList) {
        this.totalCredits = userTakenCoursesList.sumCreditOfCourses();
    }


    private List<RequirementStatusBaseEntity> values() {
        return List.of(
                graduationCategory.getLanguageBasic(),
                graduationCategory.getScienceBasic(),
                graduationCategory.getMajor(),
                graduationCategory.getHumanities(),
                graduationCategory.getEtcMandatory()
        );
    }
}
