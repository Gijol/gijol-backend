package com.gist.graduation.requirment.presentation;

import com.gist.graduation.requirment.application.GraduationRequirementStatusService;
import com.gist.graduation.requirment.domain.GraduationRequirementStatus;
import com.gist.graduation.requirment.domain.GraduationRequirementStatusRepository;
import com.gist.graduation.requirment.domain.MajorType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class GraduationRequirementStatusController {

    private final GraduationRequirementStatusService graduationRequirementStatusService;

//    public ResponseEntity<GraduationRequirementStatus> checkGraduation() {
//
//    }

    @GetMapping("/test")
    public  GraduationRequirementStatus test() throws IOException {
        return graduationRequirementStatusService.test(MajorType.EC);
    }

}
