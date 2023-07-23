package com.gist.graduation.course.domain.rawcourse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "RawCourseRepository")
public interface RawCourseRepository extends JpaRepository<RawCourse, Long> {

}
