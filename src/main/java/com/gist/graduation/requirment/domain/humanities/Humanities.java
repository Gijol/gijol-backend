package com.gist.graduation.requirment.domain.humanities;

import com.gist.graduation.requirment.domain.RequirementStatusBaseEntity;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;

public class Humanities extends RequirementStatusBaseEntity {

    public void checkRequirementByStudentId(Integer studentId, UserTakenCoursesList inputUserTakenCoursesList) {
        if (studentId >= 18) {

        }

        if (this.getMessages().isEmpty()) {
            isSatisfied();
        }

        addCredit(this.getUserTakenCoursesList().sumCreditOfCourses());
    }

    private void checkMandatoryHumanities(UserTakenCoursesList inputUserTakenCoursesList) {
//        inputUserTakenCoursesList

    }


}
