package com.gist.graduation.course;


import com.gist.graduation.course.application.CourseService;
import com.gist.graduation.course.domain.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DummyController {

    private final CourseService courseService;


    @GetMapping("/courses/dummy")
    public List<Course> getCourses() {
        return courseService.findAll();
    }

}
