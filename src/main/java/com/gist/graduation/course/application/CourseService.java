package com.gist.graduation.course.application;

import com.gist.graduation.course.domain.Course;
import com.gist.graduation.course.domain.CourseRepository;
import com.gist.graduation.course.domain.RawCourse;
import com.gist.graduation.utils.CourseListParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseTagPolicy courseTagPolicy;

    @Transactional
    public List<Course> createCourses(File file) {
        List<RawCourse> rawCourses = CourseListParser.parseToRawCourse(file);
        List<Course> courses = Course.listOf(rawCourses);
        courseTagPolicy.tagAllCourses(courses);
        return courseRepository.saveAll(courses);
    }

    @Transactional(readOnly = true)
    public List<Course> findAll() {
        return courseRepository.findAll();
    }
}
