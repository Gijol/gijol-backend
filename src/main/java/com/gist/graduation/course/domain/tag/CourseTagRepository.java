package com.gist.graduation.course.domain.tag;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CourseTagRepository extends JpaRepository<CourseTag, Long> {

    @Query("SELECT ct " +
            "FROM CourseTag ct " +
            "INNER JOIN Course  c ON c.id = ct.course.id " +
            "WHERE ct.deletedAt is null " +
            "AND ct.courseTagType = :courseTagType " +
            "AND ct.course.courseInfo.courseCode like %:courseSearchString% or ct.course.courseInfo.courseName like %:courseSearchString% ")
    Page<CourseTag> findCourseTagsByCourseTagType(CourseTagType courseTagType, String courseSearchString, Pageable pageable);
}