package com.gist.graduation.user.repository;

import com.gist.graduation.user.domain.UserTakenCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserTakenCourseRepository extends JpaRepository<UserTakenCourse, Long> {

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("UPDATE UserTakenCourse uk " +
            "SET uk.deletedAt = now(), " +
            "uk.updatedAt = now(), " +
            "uk.courseCode = null, " +
            "uk.courseName = null, " +
            "uk.courseType = null, " +
            "uk.grade = null, " +
            "uk.credit = null, " +
            "uk.yearAndSemester.year = null, " +
            "uk.yearAndSemester.semester = null " +
            "WHERE uk.user.id = :userId ")
    void deleteByUserId(@Param("userId") Long userId);
}
