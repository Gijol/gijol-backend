package com.gist.graduation.course;


import com.gist.graduation.course.application.CourseService;
import com.gist.graduation.course.domain.dto.CourseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Profile("local | dev")
public class DummyController {

    private final CourseService courseService;


    @GetMapping("/courses/dummy")
    public List<CourseResponse> getCourses() {
        return courseService.findAll();
    }

}
