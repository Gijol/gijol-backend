package com.gist.graduation.course.application;

import com.gist.graduation.course.domain.course.Course;
import com.gist.graduation.course.domain.course.CourseRepository;
import com.gist.graduation.course.domain.dto.CourseResponse;
import com.gist.graduation.course.domain.rawcourse.RawCourse;
import com.gist.graduation.utils.CourseListParser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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
        List<Course> courses = Course.listOf(rawCourses)
                .subList(0, 300);
        courseTagPolicy.tagAllCourses(courses);
        return courseRepository.saveAll(courses);
    }

    @Transactional(readOnly = true)
    public List<CourseResponse> findByMinor(String code, Pageable pageable) {
        if (code.equalsIgnoreCase("NONE")) {
            return CourseResponse.listOf(courseRepository.findAllByRandom(pageable.getPageSize(), pageable.getOffset()));
        }
        return CourseResponse.listOf(courseRepository.findCoursesByCourseCode(code, pageable).toList());

    }
}
