package com.gist.graduation.course;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document
@ToString
public class RegisteredCourse {

    @Id
    @Generated
    private String id;
    private int year;
    private String semester;
    private ArrayList<Course> courses;

    public RegisteredCourse(int year, String semester, ArrayList<Course> courses) {
        this(null, year, semester, courses);
    }

    private RegisteredCourse(String id, int year, String semester, ArrayList<Course> courses) {
        this.id = id;
        this.year = year;
        this.semester = semester;
        this.courses = courses;
    }


}
