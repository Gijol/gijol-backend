package com.gist.graduation.course.domain.dto;

import com.gist.graduation.course.domain.course.Course;
import com.gist.graduation.course.domain.tag.CourseTag;
import com.gist.graduation.course.domain.tag.CourseTagType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CourseResponse {

    private Long id;
    private String courseCode;

    private String courseName;

    private int courseCredit;

    private String prerequisite;
    private String description;

    private final List<CourseTagType> courseTags = new ArrayList<>();


    public CourseResponse(Long id, String courseCode, String courseName, int courseCredit, String prerequisite, String description) {
        this.id = id;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.courseCredit = courseCredit;
        this.prerequisite = prerequisite;
        this.description = description;
    }

    public CourseResponse(Long id, String courseCode, String courseName, int courseCredit, String prerequisite, String description, List<CourseTagType> courseTags) {
        this.id = id;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.courseCredit = courseCredit;
        this.prerequisite = prerequisite;
        this.description = description;
        this.courseTags.addAll(courseTags);
    }

    public static List<CourseResponse> listOf(List<Course> courses) {
        return courses.stream()
                .map(CourseResponse::of)
                .collect(Collectors.toList());
    }

    public static CourseResponse of(Course course) {
        List<CourseTagType> courseTags = course.getCourseTags().getCourseTags().stream()
                .map(CourseTag::getCourseTagType)
                .collect(Collectors.toList());

        return new CourseResponse(
                course.getId(),
                course.getCourseInfo().getCourseCode(),
                course.getCourseInfo().getCourseName(),
                course.getCourseInfo().getCourseCredit(),
                course.getPrerequisite(),
                course.getDescription(),
                courseTags);
    }


}
