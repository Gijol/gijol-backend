package com.gist.graduation.course.domain.course;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.util.StringUtils;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {


    default Page<Course> findCoursesByCourseCode(Pageable pageable, @Param("code") String code, @Param("courseSearchString") String courseSearchString){
        if (StringUtils.hasText(courseSearchString)){
            return findCoursesByCourseCodeAndSerachCode(pageable, code, courseSearchString);
        }
        return findCoursesByCourseCode(pageable, code);
    }


    @Query("select c from Course c where c.courseInfo.courseCode like :code% " +
            "or c.courseInfo.courseName like %:courseSearchString% ")
    Page<Course> findCoursesByCourseCodeAndSerachCode(Pageable pageable, @Param("code") String code, @Param("courseSearchString") String courseSearchString);

    @Query("select c from Course c where c.courseInfo.courseCode like :code% ")
    Page<Course> findCoursesByCourseCode(Pageable pageable, @Param("code") String code);


    default Page<Course> findAll(Pageable pageable, @Param("courseSearchString") String courseSearchString){
        if (StringUtils.hasText(courseSearchString)){
            return findAllBySearchString(pageable, courseSearchString);
        }
        return findAll(pageable);
    }

    @Query("select c from Course c " +
            "where c.courseInfo.courseCode like %:courseSearchString% " +
            "or c.courseInfo.courseName like %:courseSearchString% ")
    Page<Course> findAllBySearchString(Pageable pageable, @Param("courseSearchString") String courseSearchString);

}

