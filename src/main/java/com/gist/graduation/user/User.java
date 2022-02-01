package com.gist.graduation.user;

import com.gist.graduation.user.taken_course.UserTakenCoursesList;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@ToString
public class User {

    private UserTakenCoursesList userTakenCoursesList;
}
