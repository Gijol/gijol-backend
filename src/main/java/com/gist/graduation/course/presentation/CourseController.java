package com.gist.graduation.course.presentation;

import com.gist.graduation.course.application.CourseService;
import com.gist.graduation.course.domain.dto.CourseResponse;
import com.gist.graduation.requirment.domain.minor.MinorType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping("")
    public List<CourseResponse> getCourses(@RequestParam(required = false, defaultValue = "NONE") MinorType minorType, @PageableDefault(size = 20, page = 0) Pageable pageable) {
        return courseService.findByMinor(minorType.name(), pageable);
    }

}
