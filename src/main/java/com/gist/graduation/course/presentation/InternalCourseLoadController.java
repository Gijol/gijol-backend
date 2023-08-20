package com.gist.graduation.course.presentation;

import com.gist.graduation.config.exception.InternalApplicationException;
import com.gist.graduation.course.application.CourseService;
import com.gist.graduation.utils.FileResourceUtils;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Hidden
@RequestMapping("/api/v1/internal")
public class InternalCourseLoadController {

    private final String internalToken;
    private final CourseService courseService;
    private final FileResourceUtils fileResourceUtils;

    public InternalCourseLoadController(
            @Value("${internal.token.course}") String internalToken,
            CourseService courseService,
            FileResourceUtils fileResourceUtils
    ) {
        this.internalToken = internalToken;
        this.courseService = courseService;
        this.fileResourceUtils = fileResourceUtils;
    }

    @PostMapping("/courses/init")
    public ResponseEntity<Void> loadCourses(
            @RequestParam(value = "token", defaultValue = "") String inputToken
    ) {
        if (!StringUtils.hasText(internalToken)){
            throw new InternalApplicationException("인터널 토큰이 존재하지 않습니다.");
        }
        if (!inputToken.equals(internalToken)) {
            throw new InternalApplicationException("인터널 토큰이 유효하지 않습니다.");
        }

        courseService.createCourses(fileResourceUtils.convertPathResourceToCourseListFileAtServer());
        return ResponseEntity.ok().build();
    }


}
