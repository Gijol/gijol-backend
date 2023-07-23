package com.gist.graduation.course.domain.course;


import com.gist.graduation.course.application.RawCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("select c from Course c where c.courseInfo.courseCode like :code% ")
    Page<Course> findCoursesByCourseCode(@Param("code") String code, Pageable pageable);

    @Query(value = "select * " +
            "from course " +
            "inner join course_tag on course_tag.course_id = course.id " +
            "limit :size " +
            "offset:ofset", nativeQuery = true)
    List<Course> findAllByRandom(@Param("size") int size, @Param("ofset") long offset);

}

