package com.gist.graduation.user.taken_course;

import java.util.List;

public class UserTakenCourses {

    private final List<TakenCourse> takenCourses;

    public UserTakenCourses(List<TakenCourse> takenCourses) {
        this.takenCourses = takenCourses;
    }

    public List<TakenCourse> getTakenCourses() {
        return takenCourses;
    }


}
