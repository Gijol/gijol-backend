package com.gist.graduation.course.domain.rawcourse;

import com.gist.graduation.course.domain.CourseInfo;
import com.gist.graduation.course.domain.course.Semester;
import com.gist.graduation.utils.RegisteredCourse;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

/*
 * This entity does not think of the course code as a unique value. In other words, this can be seen as a course
 * that have same course code but different course name, year or semester. This is because there are some courses that have
 * same course code but different course name, year or semester. For example, there are two courses that have same course code.
 * */

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "raw_course")
public class RawCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private CourseInfo courseInfo;

    private String courseType;

    @Embedded
    private Semester semester;

    private String courseProfessor;

    // Date of Course
    private String courseTime;
    private String courseRoom;

    @Lob
    private String prerequisite;

    public RawCourse(String courseCode, String courseName, String courseType, int courseYear, String courseSemester, int courseCredit, String courseProfessor, String courseTime, String courseRoom, String prerequisite) {
        this(null, courseCode, courseName, courseType, courseYear, courseSemester, courseCredit, courseProfessor, courseTime, courseRoom, prerequisite);
    }

    private RawCourse(Long id, String courseCode, String courseName, String courseType, int courseYear, String courseSemester, int courseCredit, String courseProfessor, String courseTime, String courseRoom, String prerequisite) {
        this.id = id;
        this.courseInfo = new CourseInfo(courseCode, courseName, courseCredit);
        this.courseType = courseType;
        this.semester = new Semester(courseYear, courseSemester);
        this.courseProfessor = courseProfessor;
        this.courseTime = courseTime;
        this.courseRoom = courseRoom;
        this.prerequisite = prerequisite;
    }


    public static List<RawCourse> from(List<RegisteredCourse> courses) {
        return courses.stream()
                .map(RawCourse::of)
                .collect(Collectors.toList());
    }

    public static RawCourse of(RegisteredCourse course) {
        return new RawCourse(course.getCode(), course.getName(), course.getType(), course.getYear(), course.getSemester(), course.getCredit(), course.getProfessor(), course.getTime(), course.getRoom(), course.getPrerequisite());
    }
}
