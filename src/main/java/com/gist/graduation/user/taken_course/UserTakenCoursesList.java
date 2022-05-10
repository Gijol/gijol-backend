package com.gist.graduation.user.taken_course;

import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
public class UserTakenCoursesList {

    private final List<TakenCourse> takenCourses;

    public UserTakenCoursesList(List<TakenCourse> takenCourses) {
        this.takenCourses = takenCourses;
    }

    public UserTakenCoursesList() {
        this.takenCourses = new ArrayList<>();
    }

    public List<TakenCourse> getTakenCourses() {
        return takenCourses;
    }

    public boolean contains(TakenCourse takenCourse) {
        return takenCourses.contains(takenCourse);
    }

    public boolean notExist(TakenCourse takenCourse) {
        return !takenCourses.contains(takenCourse);
    }

    public boolean checkEmpty() {
        return this.takenCourses.isEmpty();
    }

    public Integer sumCreditOfCourses() {
        return takenCourses.stream()
                .mapToInt(TakenCourse::getCredit)
                .sum();
    }

    public void addAll(List<TakenCourse> inputTakenCourses) {
        takenCourses.addAll(inputTakenCourses);
    }


}
