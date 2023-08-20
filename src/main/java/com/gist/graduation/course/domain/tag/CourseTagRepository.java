package com.gist.graduation.course.domain.tag;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.util.StringUtils;

public interface CourseTagRepository extends JpaRepository<CourseTag, Long> {


    default Page<CourseTag> findCourseTagsByCourseTagType(CourseTagType courseTagType, String courseSearchString, Pageable pageable) {
        if (StringUtils.hasText(courseSearchString)){
            return findCourseTagsByCourseTagAndSearchCode(courseTagType, courseSearchString, pageable);
        }
        return findCourseTagsByCourseTagType(courseTagType, pageable);
    }

    @Query("SELECT ct " +
            "FROM CourseTag ct " +
            "INNER JOIN Course  c ON c.id = ct.course.id " +
            "WHERE ct.deletedAt is null " +
            "AND ct.courseTagType = :courseTagType " +
            "AND ct.course.courseInfo.courseCode like %:courseSearchString% or ct.course.courseInfo.courseName like %:courseSearchString% ")
    Page<CourseTag> findCourseTagsByCourseTagAndSearchCode(CourseTagType courseTagType, String courseSearchString, Pageable pageable);

    @Query("SELECT ct " +
            "FROM CourseTag ct " +
            "INNER JOIN Course  c ON c.id = ct.course.id " +
            "WHERE ct.deletedAt is null " +
            "AND ct.courseTagType = :courseTagType ")
    Page<CourseTag> findCourseTagsByCourseTagType(CourseTagType courseTagType, Pageable pageable);
}