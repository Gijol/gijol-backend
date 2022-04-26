package com.gist.graduation.requirment.domain;

import com.gist.graduation.requirment.domain.major.*;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;
import org.apache.logging.log4j.util.TriConsumer;

public enum MajorType {
    EC(EECSMajor::checkverified),
    MA(MaterialScienceMajor::checkverified),
    MC(MechanicalEngineeringMajor::checkverified),
    EV(EnvironmentMajor::checkverified),
    BS(BiologyMajor::checkverified),
    PS(PhysicsMajor::checkverified),
    CH(ChemistryMajor::checkverified);


    private final TriConsumer<UserTakenCoursesList, Major, Integer> checkMajorMandatory;

    MajorType(TriConsumer<UserTakenCoursesList, Major, Integer> checkMajorMandatory) {
        this.checkMajorMandatory = checkMajorMandatory;
    }

    public void checkMajorStatus(UserTakenCoursesList inputUserTakenCoursesList, Major major, Integer studentId){
        checkMajorMandatory.accept(inputUserTakenCoursesList, major, studentId);
    }
}
