package com.gist.graduation.course.domain.rawcourse;

import com.gist.graduation.common.BaseEntity;
import com.gist.graduation.course.domain.CourseInfo;
import com.gist.graduation.course.domain.course.vo.SemesterInfo;
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
@Table(name = "raw_course", indexes = {
        @Index(name = "idx_raw_course_course_code", columnList = "course_code")
})
public class RawCourse extends BaseEntity {

    @Embedded
    private CourseInfo courseInfo;

    private String courseType;

    @Embedded
    private SemesterInfo semesterInfo;

    private String courseProfessor;

    // Date of Course
    private String courseTime;
    private String courseRoom;

    @Lob
    private String prerequisite;

    @Column(name = "course_id")
    private Long courseId;

    public RawCourse(String courseCode, String courseName, String courseType, int courseYear, String courseSemester, int courseCredit, String courseProfessor, String courseTime, String courseRoom, String prerequisite) {
        this.courseInfo = new CourseInfo(courseName, courseCode, courseCredit);
        this.courseType = courseType;
        this.semesterInfo = new SemesterInfo(courseYear, courseSemester);
        this.courseProfessor = courseProfessor;
        this.courseTime = courseTime;
        this.courseRoom = courseRoom;
        this.prerequisite = prerequisite;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public static List<RawCourse> from(List<RegisteredCourse> courses) {
        return courses.stream()
                .filter(s -> !s.getCode().startsWith("CM")) // 파싱 에러 코드 제거
                .map(RawCourse::of)
                .collect(Collectors.toList());
    }

    public static RawCourse of(RegisteredCourse course) {
        return new RawCourse(course.getCode().trim(), course.getName().trim(), course.getType(), course.getYear(), course.getSemester(), course.getCredit(), course.getProfessor(), course.getTime(), course.getRoom(), course.getPrerequisite());
    }
}
