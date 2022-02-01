package com.gist.graduation.requirment.domain;


import com.gist.graduation.user.taken_course.UserTakenCoursesList;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class BasicScience extends RequirementStatusBaseEntity {

    public void checkRequirementByStudentId(Integer studentId, UserTakenCoursesList inputUserTakenCoursesList) {
        if (studentId >= 18) {
        }

        if (getMessage().isEmpty()) {
            isSatisfied();
        }

        addCredit(this.getUserTakenCoursesList().sumCreditOfCourses());
    }

    private void checkMathFrom2018() {

    }

}
