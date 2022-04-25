package com.gist.graduation.requirment.domain;

import com.gist.graduation.requirment.domain.major.EECSMajor;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;
import org.apache.logging.log4j.util.TriConsumer;

public enum MajorType2 {
    EC(EECSMajor::checkverified),
    MA(EECSMajor::checkverified),
    MC(EECSMajor::checkverified),
    EV(EECSMajor::checkverified),
    BS(EECSMajor::checkverified),
    PS(EECSMajor::checkverified),
    CH(EECSMajor::checkverified);


    private final TriConsumer<UserTakenCoursesList, Major, Integer> checkMajorMandatory;

    MajorType2(TriConsumer<UserTakenCoursesList, Major, Integer> checkMajorMandatory) {
        this.checkMajorMandatory = checkMajorMandatory;
    }

    public void checkMajorStatus(UserTakenCoursesList inputUserTakenCoursesList, Major major, Integer studentId){
        checkMajorMandatory.accept(inputUserTakenCoursesList, major, studentId);
    }
}
