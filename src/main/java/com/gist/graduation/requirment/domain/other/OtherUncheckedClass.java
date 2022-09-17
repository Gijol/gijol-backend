package com.gist.graduation.requirment.domain.other;

import com.gist.graduation.requirment.domain.GraduationRequirementStatus;
import com.gist.graduation.requirment.domain.RequirementStatusBaseEntity;
import com.gist.graduation.user.taken_course.TakenCourse;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;

import java.util.List;
import java.util.stream.Collectors;

public class OtherUncheckedClass extends RequirementStatusBaseEntity {

    public static final int TOTAL_CREDITS = 130;

    public void checkRequirementByStudentId(Integer studentId, UserTakenCoursesList inputUserTakenCoursesList, GraduationRequirementStatus graduationRequirementStatus) {
        if (studentId >= 18) {
            List<TakenCourse> exceptOtherUncheckedClasses = graduationRequirementStatus.listOfExceptOtherUncheckedClasses();

            this.getUserTakenCoursesList().addAll(inputUserTakenCoursesList.getTakenCourses().stream()
                    .filter(s -> !exceptOtherUncheckedClasses.contains(s))
                    .collect(Collectors.toList()));
        }

        if (this.getMessages().isEmpty()) {
            isSatisfied();
        }

        setMinConditionCredits(TOTAL_CREDITS - graduationRequirementStatus.getTotalCredits());
        addCredit(this.getUserTakenCoursesList().sumCreditOfCourses());
    }

}
