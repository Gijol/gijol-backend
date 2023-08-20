package com.gist.graduation.course.domain.tag;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseTagRepository extends JpaRepository<CourseTag, Long> {

    Page<CourseTag> findCourseTagsByCourseTagType(CourseTagType courseTagType, Pageable pageable);
}