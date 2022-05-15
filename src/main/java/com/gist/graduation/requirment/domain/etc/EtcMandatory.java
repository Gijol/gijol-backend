package com.gist.graduation.requirment.domain.etc;


import com.gist.graduation.requirment.domain.RequirementStatusBaseEntity;
import com.gist.graduation.requirment.domain.major.MajorType;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;

public class EtcMandatory extends RequirementStatusBaseEntity {

    @Override
    public void checkRequirementByStudentId(Integer studentId, UserTakenCoursesList inputUserTakenCoursesList, MajorType majorType) {
        if (studentId >= 18) {
            ArtAndSport.checkArtAndSport(this, studentId, inputUserTakenCoursesList);
            Research.checkResearch(this, inputUserTakenCoursesList);
            OthersEtc.checkOtherETC(this, studentId, inputUserTakenCoursesList);
        }

        if (this.getMessages().isEmpty()) {
            isSatisfied();
        }

        addCredit(this.getUserTakenCoursesList().sumCreditOfCourses());
    }
}
