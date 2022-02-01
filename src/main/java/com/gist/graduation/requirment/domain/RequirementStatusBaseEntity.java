package com.gist.graduation.requirment.domain;

import com.gist.graduation.user.taken_course.TakenCourse;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@Document
public class RequirementStatusBaseEntity {

    private final UserTakenCoursesList userTakenCoursesList;

    private Integer totalCredits;

    private Boolean satisfied;

    private final List<String> message;

    public RequirementStatusBaseEntity() {
        this.userTakenCoursesList = new UserTakenCoursesList(new ArrayList<>());
        this.totalCredits = 0;
        this.satisfied = false;
        this.message = new ArrayList<>();
    }

    public void addCredit(Integer credit) {
        this.totalCredits += credit;
    }

    public void isSatisfied() {
        this.satisfied = true;
    }

    public void addCourse(TakenCourse course) {
        this.userTakenCoursesList.getTakenCourses().add(course);
    }


}
