package com.gist.graduation.course;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document()
@ToString
public class Course {

    @Id
    @Generated
    private String id;
    private int year;
    private String semester;
    private ArrayList<CoreCourseInfo> coreCourseInfos;

    public Course(int year, String semester, ArrayList<CoreCourseInfo> coreCourseInfos) {
        this(null, year, semester, coreCourseInfos);
    }

    private Course(String id, int year, String semester, ArrayList<CoreCourseInfo> coreCourseInfos) {
        this.id = id;
        this.year = year;
        this.semester = semester;
        this.coreCourseInfos = coreCourseInfos;
    }
}
