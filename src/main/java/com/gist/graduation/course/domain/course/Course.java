package com.gist.graduation.course.domain.course;

import com.gist.graduation.course.domain.CourseInfo;
import com.gist.graduation.course.domain.rawcourse.RawCourse;
import com.gist.graduation.course.domain.tag.CourseTag;
import com.gist.graduation.course.domain.tag.CourseTags;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    public boolean equalCourseInfo(Course course) {
        return this.courseInfo.equals(course.courseInfo);
    }

    public boolean belongToCourseInfo(List<CourseInfo> courseInfo) {
        return courseInfo.stream()
                .anyMatch(s -> s.equals(this.courseInfo));
    }


    public static List<Course> listOf(List<RawCourse> rawCourses) {
        List<Course> courses = new ArrayList<>();

        for (RawCourse rawCourse : rawCourses) {
            Course newCourse = of(rawCourse);
            if (existCourses(newCourse, courses)) {
                continue;
            }
            courses.add(newCourse);
        }

        return courses;
    }

    private static boolean existCourses(Course newCourse, List<Course> courses) {
        for (Course course : courses) {
            if (course.equalCourseInfo(newCourse)) {
                return true;
            }
        }
        return false;
    }


    public static Course of(RawCourse rawCourse) {
        CourseInfo courseInfo = rawCourse.getCourseInfo();
        return new Course(courseInfo.getCourseCode(), courseInfo.getCourseName(), courseInfo.getCourseCredit(), rawCourse.getPrerequisite());
    }
}
