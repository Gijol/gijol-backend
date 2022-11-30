package com.gist.graduation.requirment.domain.minor;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.gist.graduation.config.exception.ApplicationException;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;

public enum MinorType {

    EC,
    MA,
    MC,
    EV,
    BS,
    PS,
    CH,
    MM,
    CT,
    LH,
    EB,
    MB,
    SS,
    PP,
    MD,
    FE,
    IR,
    AI,
    NONE;

    public void checkMathMinor(UserTakenCoursesList inputUserTakenCoursesList){


    }

    @JsonCreator
    public static MinorType fromMajorType(String minor) {
        for (MinorType minorType : MinorType.values()) {
            if (minorType.name().equals(minor)) {
                return minorType;
            }
        }
        throw new ApplicationException("존재하지 않는 전공입니다.");
    }
}
