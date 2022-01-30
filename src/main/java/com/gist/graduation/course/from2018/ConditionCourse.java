package com.gist.graduation.course.from2018;

import com.gist.graduation.course.Course;
import lombok.Generated;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@ToString
@Document
public class ConditionCourse {

    @Id
    @Generated
    private String id;

    private String classificationName;

    private int totalCredit;

    private List<Course> courses;
}
