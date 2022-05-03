package com.gist.graduation.requirment.domain;

import com.gist.graduation.user.taken_course.UserTakenCoursesList;

public class Major extends RequirementStatusBaseEntity {

    public void checkRequirementByStudentId(Integer studentId, UserTakenCoursesList inputUserTakenCoursesList, MajorType majorType) {
        majorType.checkMajorStatus(inputUserTakenCoursesList, this, studentId);

        if (this.getMessages().isEmpty()) {
            isSatisfied();
        }
    }
}
