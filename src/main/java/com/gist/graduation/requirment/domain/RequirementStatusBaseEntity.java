package com.gist.graduation.requirment.domain;

import com.gist.graduation.requirment.domain.major.MajorType;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class RequirementStatusBaseEntity {

    private final UserTakenCoursesList userTakenCoursesList;

    private Integer totalCredits;

    private Integer minConditionCredits;

    private Boolean satisfied;

    private final List<String> messages;


    public RequirementStatusBaseEntity() {
        this.userTakenCoursesList = new UserTakenCoursesList(new ArrayList<>());
        this.totalCredits = 0;
        this.minConditionCredits = 0;
        this.satisfied = false;
        this.messages = new ArrayList<>();
    }

    public void checkRequirementByStudentId(Integer studentId, UserTakenCoursesList inputUserTakenCoursesList, MajorType majorType) {

    }

    public void addCredit(Integer credit) {
        this.totalCredits += credit;
    }

    public void setMinConditionCredits(Integer minConditionCredits){
        this.minConditionCredits = minConditionCredits;
    }

    public void isSatisfied() {
        this.satisfied = true;
    }

    public void addMessage(String message) {
        this.messages.add(message);
    }
}
