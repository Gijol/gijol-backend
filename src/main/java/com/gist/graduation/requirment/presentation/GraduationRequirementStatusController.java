package com.gist.graduation.requirment.presentation;

import com.gist.graduation.requirment.application.GraduationRequirementStatusService;
import com.gist.graduation.requirment.domain.GraduationRequirementStatus;
import com.gist.graduation.requirment.dto.DemoGradeToCheckRequest;
import com.gist.graduation.requirment.dto.FeedbackRequest;
import com.gist.graduation.requirment.dto.GradeToCheckRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/graduation")
@Deprecated
public class GraduationRequirementStatusController {

    private final GraduationRequirementStatusService graduationRequirementStatusService;

    @PostMapping("")
    public ResponseEntity<GraduationRequirementStatus> checkGraduation(GradeToCheckRequest request) throws IOException {
        GraduationRequirementStatus graduationRequirementStatus = graduationRequirementStatusService.checkGraduationCondition(request);
        return ResponseEntity.ok().body(graduationRequirementStatus);
    }

    @PostMapping("/demo")
    public ResponseEntity<GraduationRequirementStatus> checkGraduationDemo(DemoGradeToCheckRequest request) throws IOException {
        GraduationRequirementStatus graduationRequirementStatus = graduationRequirementStatusService.checkGraduationConditionForDemo(request);
        return ResponseEntity.ok().body(graduationRequirementStatus);
    }

    @PostMapping("/feedback")
    public ResponseEntity<Void> feedbackOnServices(@RequestBody FeedbackRequest request) {
        log.info("[feedback] " + request.getMessage());
        return ResponseEntity.created(URI.create("")).build();
    }

}
