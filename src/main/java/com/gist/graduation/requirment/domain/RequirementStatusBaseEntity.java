package com.gist.graduation.requirment.domain;

import com.gist.graduation.user.taken_course.TakenCourse;
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

    private Integer maxCredits;

    private Boolean satisfied;

    private final List<String> messages;


    public RequirementStatusBaseEntity() {
        this.userTakenCoursesList = new UserTakenCoursesList(new ArrayList<>());
        this.totalCredits = 0;
        this.maxCredits = 0;
        this.satisfied = false;
        this.messages = new ArrayList<>();
    }

    public void addCredit(Integer credit) {
        this.totalCredits += credit;
    }

    public void setMaxCredits(Integer maxCredits) {
        this.maxCredits = maxCredits;
    }

    public void isSatisfied() {
        this.satisfied = true;
    }

    public void addCourse(TakenCourse course) {
        this.userTakenCoursesList.getTakenCourses().add(course);
    }

    public void addMessage(String message) {
        this.messages.add(message);
    }
}
