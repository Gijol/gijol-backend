package com.gist.graduation.course.domain.course;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface CourseRepository extends JpaRepository<Course, Long> {

    default Page<Course> findCoursesByCourseCodeWithNone(String code, Pageable pageable) {
        if (code.equalsIgnoreCase("NONE")){
            return findAllByRandom(pageable);
        }

        return findCoursesByCourseCode(code, pageable);
    }

    @Query("select c from Course c where c.courseInfo.courseCode like :code% ")
    Page<Course> findCoursesByCourseCode(@Param("code") String code, Pageable pageable);

    @Query("select c from Course c order by rand() ")
    Page<Course> findAllByRandom(Pageable pageable);

}

