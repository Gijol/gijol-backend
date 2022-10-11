package com.gist.graduation.course.application;

import com.gist.graduation.course.domain.rawcourse.RawCourse;
import com.gist.graduation.course.domain.rawcourse.RawCourseRepository;
import com.gist.graduation.utils.CourseListParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RawCourseService {

    private final RawCourseRepository rawCourseRepository;

    // save raw course from file
    @Transactional
    public List<RawCourse> saveRawCourses(File file) {
        List<RawCourse> rawCourses = CourseListParser.parseToRawCourse(file);
        return rawCourseRepository.saveAll(rawCourses);
    }

}
