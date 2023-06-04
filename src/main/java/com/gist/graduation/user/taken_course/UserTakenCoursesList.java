package com.gist.graduation.user.taken_course;

import com.gist.graduation.course.domain.CourseInfo;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        return this.takenCourses;
    }

    public boolean contains(TakenCourse takenCourse) {
        return this.takenCourses.contains(takenCourse);
    }

    public boolean contains(CourseInfo courseInfo) {
        return this.takenCourses.stream()
                .anyMatch(s -> s.equalsCourseInfo(courseInfo));
    }

    public boolean containsAll(List<CourseInfo> courseInfos) {
        return courseInfos.stream()
                .allMatch(this::contains);
    }

    public boolean containsAny(List<CourseInfo> courseInfos) {
        return courseInfos.stream()
                .anyMatch(this::contains);
    }


    // 하나라도 포함되면 False 반환 ,아무것도 없으면 True 반환
    public boolean notExistAny(List<CourseInfo> courseInfos) {
        return courseInfos.stream()
                .noneMatch(this::contains);
    }


    public boolean notExist(TakenCourse takenCourse) {
        return !this.takenCourses.contains(takenCourse);
    }

    public boolean notExist(CourseInfo courseInfo) {
        return !this.contains(courseInfo);
    }

    public Integer sumCreditOfCourses() {
        return takenCourses.stream()
                .mapToInt(TakenCourse::getCredit)
                .sum();
    }

    public void add(TakenCourse inputTakenCourse) {
        this.takenCourses.add(inputTakenCourse);
    }

    public void addAll(List<TakenCourse> inputTakenCourses) {
        this.takenCourses.addAll(inputTakenCourses);
    }

    public List<TakenCourse> findCoursesByType(CourseType courseType) {
        return this.takenCourses.stream()
                .filter(s -> s.getCourseType().equals(courseType))
                .collect(Collectors.toList());
    }

}
