package com.gist.graduation.requirment.presentation;

import com.gist.graduation.requirment.application.GraduationRequirementStatusService;
import com.gist.graduation.requirment.domain.GraduationRequirementStatus;
import com.gist.graduation.requirment.domain.major.MajorType;
import com.gist.graduation.requirment.dto.GradeToCheckRequest;
import com.gist.graduation.user.taken_course.TakenCourse;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;
import com.gist.graduation.utils.HumanitiesListParser;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Profile("local")
@RequiredArgsConstructor
public class TestController {

    private final GraduationRequirementStatusService graduationRequirementStatusService;

    @GetMapping("/upload")
    public String formTest(@ModelAttribute GradeToCheckRequest request) {
        return "upload";
    }

    @PostMapping("/upload")
    public ResponseEntity<GraduationRequirementStatus> formUploadTest(@ModelAttribute GradeToCheckRequest request, Model model) throws IOException {
        GraduationRequirementStatus graduationRequirementStatus = graduationRequirementStatusService.checkGraduationCondition(request);
        return ResponseEntity.ok().body(graduationRequirementStatus);

    }

    @GetMapping("/empty")
    public String formEmptyTest(Model model) {
        GraduationRequirementStatus graduationRequirementStatus = new GraduationRequirementStatus();
        graduationRequirementStatus.checkGraduationRequirements(20, new UserTakenCoursesList(), MajorType.EC);
        model.addAttribute("result", graduationRequirementStatus);
        return "result";
    }

    @GetMapping("/humanities")
    @ResponseBody
    public List<TakenCourse> getHumanities() {
        List<TakenCourse> ppeCoursesList = HumanitiesListParser.getPPECoursesList();
        List<TakenCourse> husCoursesList = HumanitiesListParser.getHUSCoursesList();
        List<TakenCourse> collect = ppeCoursesList.stream().filter(husCoursesList::contains).sorted((a, b) -> b.getCourseName().compareTo(a.getCourseName())).collect(Collectors.toList());
        return collect;
    }
}
