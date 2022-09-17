package com.gist.graduation.course.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private CourseInfo courseInfo;

    @Embedded
    private CourseTags courseTags;

    @Lob
    private String prerequisite;

    public Course(String courseCode, String courseName, int courseCredit, String prerequisite) {
        this(null, courseCode, courseName, courseCredit, prerequisite);
    }

    private Course(Long id, String courseCode, String courseName, int courseCredit, String prerequisite) {
        this.id = id;
        this.courseInfo = new CourseInfo(courseCode, courseName, courseCredit);
        this.courseTags = new CourseTags();
        this.prerequisite = prerequisite;
    }


    public void addTag(CourseTag courseTag) {
        courseTags.addCourseTag(courseTag);
    }

    public static List<Course> listOf(List<RawCourse> rawCourses) {
        return rawCourses.stream()
                .map(Course::of)
                .collect(Collectors.toList());
    }

    public static Course of(RawCourse rawCourse) {
        CourseInfo courseInfo = rawCourse.getCourseInfo();
        return new Course(courseInfo.getCourseCode(), courseInfo.getCourseName(), courseInfo.getCourseCredit(), rawCourse.getPrerequisite());
    }
}
