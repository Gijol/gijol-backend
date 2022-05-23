package com.gist.graduation.requirment.domain.science;

import com.gist.graduation.user.taken_course.TakenCourse;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;
import lombok.Getter;

import java.util.List;

import static com.gist.graduation.requirment.domain.science.ScienceEnum.Status.EMPTY;

@Getter
public class ScienceBlock {
    private final UserTakenCoursesList userTakenCoursesList;
    private final ScienceEnum.Status status;
    private String type;

    public ScienceBlock() {
        this.userTakenCoursesList = new UserTakenCoursesList();
        this.status = EMPTY;
    }

    public ScienceBlock(List<TakenCourse> userTakenCoursesList, ScienceEnum.Status status, String type) {
        this.userTakenCoursesList = new UserTakenCoursesList(userTakenCoursesList);
        this.status = status;
        this.type = type;
    }

    public void addAll(List<TakenCourse> takenCourseList) {
        this.userTakenCoursesList.addAll(takenCourseList);
    }

    public int getSize() {
        return this.userTakenCoursesList.getTakenCourses().size();
    }

}
