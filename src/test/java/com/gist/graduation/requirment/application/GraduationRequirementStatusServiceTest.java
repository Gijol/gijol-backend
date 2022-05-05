package com.gist.graduation.requirment.application;

import com.gist.graduation.requirment.domain.GraduationRequirementStatus;
import com.gist.graduation.requirment.domain.MajorType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class GraduationRequirementStatusServiceTest {


    private GraduationRequirementStatusService graduationRequirementStatusService;

    @BeforeEach
    public void setup(){
        graduationRequirementStatusService = new GraduationRequirementStatusService();
    }

    @Test
    void checkGrade() throws IOException {
        ClassPathResource gradeResource = new ClassPathResource("test-grade/grade_report.xls");
        GraduationRequirementStatus graduationRequirementStatus = graduationRequirementStatusService.checkGradeTest(gradeResource.getFile(), MajorType.EC);
        System.out.println(graduationRequirementStatus);
    }
}