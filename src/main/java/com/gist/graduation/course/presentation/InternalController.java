package com.gist.graduation.course.presentation;

import com.gist.graduation.config.exception.InternalApplicationException;
import com.gist.graduation.course.application.CourseService;
import com.gist.graduation.requirment.domain.GraduationRequirementStatus;
import com.gist.graduation.user.application.UserService;
import com.gist.graduation.utils.FileResourceUtils;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@Hidden
@RequestMapping("/api/v1/internal")
public class InternalController {

    private final String internalToken;
    private final CourseService courseService;
    private final FileResourceUtils fileResourceUtils;
    private final UserService userService;

    public InternalController(
            @Value("${internal.token.course}") String internalToken,
            CourseService courseService,
            FileResourceUtils fileResourceUtils,
            UserService userService
    ) {
        this.internalToken = internalToken;
        this.courseService = courseService;
        this.fileResourceUtils = fileResourceUtils;
        this.userService = userService;
    }

    @PostMapping("/courses/init")
    public ResponseEntity<Void> loadCourses(
            @RequestParam(value = "token", defaultValue = "") String inputToken
    ) {
        validateInternalToken(inputToken);
        courseService.createCourses(fileResourceUtils.convertPathResourceToCourseListFileAtServer());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/graduation/requirements/{userId}")
    public ResponseEntity<GraduationRequirementStatus> checkGraduationRequirement(
            @PathVariable("userId") Long userId,
            @RequestParam(value = "token", defaultValue = "") String inputToken
    ) {
        validateInternalToken(inputToken);
        GraduationRequirementStatus graduationRequirementStatus = userService.checkGraduationRequirementForUser(userId);
        return ResponseEntity.ok(graduationRequirementStatus);
    }

    private void validateInternalToken(String inputToken) {
        if (!StringUtils.hasText(internalToken)) {
            throw new InternalApplicationException("인터널 토큰이 존재하지 않습니다.");
        }
        if (!inputToken.equals(internalToken)) {
            throw new InternalApplicationException("인터널 토큰이 유효하지 않습니다.");
        }
    }


}
