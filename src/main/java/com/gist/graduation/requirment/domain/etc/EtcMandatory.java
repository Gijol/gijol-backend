package com.gist.graduation.requirment.domain.etc;


import com.gist.graduation.requirment.domain.RequirementStatusBaseEntity;
import com.gist.graduation.requirment.domain.major.MajorType;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;

public class EtcMandatory extends RequirementStatusBaseEntity {
    private static final Integer MIN_CREDIT_FROM_2018 = 7;
    private static final Integer MIN_CREDIT_FROM_2021 = 8;

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

        setCredits(studentId);
        addCredit(this.getUserTakenCoursesList().sumCreditOfCourses());
    }

    private void setCredits(Integer studentId){
        if (studentId >= 21) {
            this.setMinConditionCredits(MIN_CREDIT_FROM_2021);
            return;
        }
        this.setMinConditionCredits(MIN_CREDIT_FROM_2018);
    }
}
