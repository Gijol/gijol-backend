package com.gist.graduation.requirment.presentation;

import com.gist.graduation.requirment.application.GraduationRequirementStatusService;
import com.gist.graduation.requirment.domain.GraduationRequirementStatus;
import com.gist.graduation.requirment.domain.MajorType;
import com.gist.graduation.requirment.dto.GradeToCheckRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/graduation")
public class GraduationRequirementStatusController {

    private final GraduationRequirementStatusService graduationRequirementStatusService;

    @PostMapping("")
    public ResponseEntity<GraduationRequirementStatus> checkGraduation(@RequestBody GradeToCheckRequest request) throws IOException {
        GraduationRequirementStatus graduationRequirementStatus = graduationRequirementStatusService.checkGraduationCondition(request);
        return ResponseEntity.ok().body(graduationRequirementStatus);

    }

    @GetMapping("/test")
    public GraduationRequirementStatus test() throws IOException {
        return graduationRequirementStatusService.test(MajorType.EC);
    }

}
