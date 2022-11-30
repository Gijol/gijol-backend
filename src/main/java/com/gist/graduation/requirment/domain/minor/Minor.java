package com.gist.graduation.requirment.domain.minor;

import com.gist.graduation.requirment.domain.RequirementStatusBaseEntity;
import com.gist.graduation.requirment.domain.major.MajorType;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;

public class Minor extends RequirementStatusBaseEntity {

    @Override
    public void checkRequirementByStudentId(Integer studentId, UserTakenCoursesList inputUserTakenCoursesList, MajorType majorType) {
    }

    public void checkRequirementByStudentId(Integer studentId, UserTakenCoursesList inputUserTakenCoursesList, MinorType minorType) {
        if (minorType.equals(MinorType.MM)) {
            minorType.checkMathMinor(studentId, this, inputUserTakenCoursesList);
        }
    }
}
