package com.gist.graduation.course.domain.course;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("select c from Course c where c.courseInfo.courseCode like :code% ")
    List<Course> findCoursesByCourseCode(Pageable pageable, @Param("code") String code);

}

