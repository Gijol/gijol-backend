package com.gist.graduation.requirment.domain;

import com.gist.graduation.user.taken_course.UserTakenCoursesList;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Major extends RequirementStatusBaseEntity {

    public void checkRequirementByStudentId(Integer studentId, UserTakenCoursesList inputUserTakenCoursesList, MajorType majorType) {
        majorType.checkMajorStatus(inputUserTakenCoursesList, this, studentId);

        if (this.getMessages().isEmpty()) {
            isSatisfied();
        }

        addCredit(this.getUserTakenCoursesList().sumCreditOfCourses());
    }
}
