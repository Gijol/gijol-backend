package com.gist.graduation.requirment.presentation;

import com.gist.graduation.requirment.application.GraduationRequirementStatusService;
import com.gist.graduation.requirment.domain.GraduationRequirementStatus;
import com.gist.graduation.requirment.dto.GradeToCheckRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class TestController {

    private final GraduationRequirementStatusService graduationRequirementStatusService;


    @GetMapping("/upload")
    public String formTest(@ModelAttribute GradeToCheckRequest request){
        return "upload";
    }

    @PostMapping("/upload")
    public String formUploadTest(@ModelAttribute GradeToCheckRequest request, Model model) throws IOException {
        GraduationRequirementStatus graduationRequirementStatus = graduationRequirementStatusService.checkGraduationCondition(request);
        model.addAttribute("result", graduationRequirementStatus);
        return "result";
    }


}
