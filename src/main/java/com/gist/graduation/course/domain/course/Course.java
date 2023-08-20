package com.gist.graduation.course.domain.course;

import com.gist.graduation.common.BaseEntity;
import com.gist.graduation.course.domain.CourseInfo;
import com.gist.graduation.course.domain.rawcourse.RawCourse;
import com.gist.graduation.course.domain.tag.CourseTag;
import com.gist.graduation.course.domain.tag.CourseTags;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;
import org.thymeleaf.util.StringUtils;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Lob;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Where(clause = "deleted_at is null")
public class Course extends BaseEntity {

    @Embedded
    private CourseInfo courseInfo;

    @Embedded
    private CourseTags courseTags;

    @Lob
    private String prerequisite;

    @Lob
    private String description;

    private Course(String courseCode, String courseName, int courseCredit, String prerequisite) {
        this.courseInfo = new CourseInfo(courseName, courseCode, courseCredit);
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
                .anyMatch(s -> s.getCourseCode().equals(this.courseInfo.getCourseCode()));
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
        return new Course(courseInfo.getCourseCode().trim(), courseInfo.getCourseName().trim(), courseInfo.getCourseCredit(), rawCourse.getPrerequisite());
    }

    public void updateDescription(String description) {
        if (StringUtils.isEmpty(description)) {
            this.description = null;
            return;
        }
        this.description = description;
    }
}
