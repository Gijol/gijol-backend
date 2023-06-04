package com.gist.graduation.course.application;

import com.gist.graduation.course.domain.course.Course;
import com.gist.graduation.course.domain.course.CourseRepository;
import com.gist.graduation.course.domain.dto.CourseResponse;
import com.gist.graduation.course.domain.rawcourse.RawCourse;
import com.gist.graduation.utils.CourseListParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseTagPolicy courseTagPolicy;

    @Transactional
    public List<Course> createCourses(File file) {
        List<RawCourse> rawCourses = CourseListParser.parseToRawCourse(file);
        List<Course> courses = Course.listOf(rawCourses)
                .subList(0, 100);
        courseTagPolicy.tagAllCourses(courses);
        return courseRepository.saveAll(courses);
    }

    @Transactional(readOnly = true)
    public List<CourseResponse> findAll() {
        List<Course> courses = courseRepository.findAll();
        Collections.shuffle(courses);
        return CourseResponse.listOf(courses);
    }

    public List<CourseResponse> findByMinor(String minorType) {
        List<Course> courses = courseRepository.findAll();
        if (minorType.equals("NONE")) {
            return CourseResponse.listOf(courses);
        }
        Set<Course> filtered = courses.stream().filter(s -> s.getCourseInfo().belongToCoursesCode(List.of(minorType))).collect(Collectors.toSet());
        return CourseResponse.listOf(filtered);

    }
}
