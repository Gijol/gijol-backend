package com.gist.graduation.course.presentation;

import com.gist.graduation.course.application.CourseService;
import com.gist.graduation.course.domain.dto.CourseResponse;
import com.gist.graduation.requirment.domain.minor.MinorType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    @ApiParam(value = "page", type = "integer",example = "20")
    public List<CourseResponse> getCourses(@RequestParam(required = false, defaultValue = "NONE") MinorType minorType,
                                           @RequestParam(required = false, defaultValue = "0") int page,
                                           @RequestParam(required = false, defaultValue = "20") int size
                                           )
    {
        return courseService.findByMinor(minorType.name(), PageRequest.of(page, size));
    }

}
