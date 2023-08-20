package com.gist.graduation.course.domain.rawcourse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "RawCourseRepository")
public interface RawCourseRepository extends JpaRepository<RawCourse, Long> {

    List<RawCourse> findAllByCourseId(Long courseId);
}
