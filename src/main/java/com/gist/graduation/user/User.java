package com.gist.graduation.user;

import com.gist.graduation.user.taken_course.UserTakenCoursesList;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class User {

    private UserTakenCoursesList userTakenCoursesList;
}
