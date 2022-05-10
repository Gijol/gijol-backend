package com.gist.graduation.requirment.domain.science;

import com.gist.graduation.user.taken_course.TakenCourse;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

import static com.gist.graduation.requirment.domain.science.ScienceEnum.Status.EMPTY;

@Getter
public class ScienceBlock {
    private final UserTakenCoursesList userTakenCoursesList;
    private ScienceEnum.Status status;

    public ScienceBlock() {
        this.userTakenCoursesList = new UserTakenCoursesList();
        this.status = EMPTY;
    }

    public ScienceBlock(List<TakenCourse> userTakenCoursesList, ScienceEnum.Status status) {
        this.userTakenCoursesList = new UserTakenCoursesList(userTakenCoursesList);
        this.status = status;
    }

    public void addAll(List<TakenCourse> takenCourseList) {
        this.userTakenCoursesList.addAll(takenCourseList);
    }

    public void setStatus(ScienceEnum.Status status){
            this.status = status;
        }

}
