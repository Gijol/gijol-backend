package com.gist.graduation.course.presentation;

import com.gist.graduation.course.application.CourseService;
import com.gist.graduation.course.domain.dto.CourseResponse;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping("")
    @ApiParam(value = "page", type = "integer", example = "20")
    public ResponseEntity<Page<CourseResponse>> getCourses(@RequestParam(required = false, defaultValue = "NONE") CourseSearchCode courseSearchCode,
                                                           @RequestParam(required = false, defaultValue = "0") int page,
                                                           @RequestParam(required = false, defaultValue = "20") int size
    ) {
        return ResponseEntity.ok(courseService.findByCourseSearchCode(courseSearchCode.name(), PageRequest.of(page, size)));
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<?> getCourseHistory(@PathVariable("courseId") Long courseId) {
        return ResponseEntity.ok(courseService.findCourseHistoriesByCourseId(courseId));
    }

}
