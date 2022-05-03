package com.gist.graduation.requirment.application;

import com.gist.graduation.requirment.domain.GraduationRequirementStatus;
import com.gist.graduation.requirment.domain.GraduationRequirementStatusRepository;
import com.gist.graduation.requirment.domain.MajorType;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;
import com.gist.graduation.utils.UserTakenCousrseParser;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class GraduationRequirementStatusService {

    public GraduationRequirementStatus checkGrade(MultipartFile file, MajorType majorType) throws IOException {
        UserTakenCoursesList userTakenCoursesList = UserTakenCousrseParser.parseUserTakenCousrse(file.getResource().getFile());
        Integer studentId = UserTakenCousrseParser.getStudentId(file.getResource().getFile());
        GraduationRequirementStatus graduationRequirementStatus = new GraduationRequirementStatus();
        graduationRequirementStatus.checkGraduationRequirements(studentId, userTakenCoursesList, majorType);

        return graduationRequirementStatus;

    }

    public GraduationRequirementStatus checkGradeTest(File file, MajorType majorType) throws IOException {
        UserTakenCoursesList userTakenCoursesList = UserTakenCousrseParser.parseUserTakenCousrse(file);
        Integer studentId = UserTakenCousrseParser.getStudentId(file);
        GraduationRequirementStatus graduationRequirementStatus = new GraduationRequirementStatus();
        graduationRequirementStatus.checkGraduationRequirements(studentId, userTakenCoursesList, majorType);

        return graduationRequirementStatus;

    }
    public GraduationRequirementStatus test(MajorType majorType) throws IOException {
        ClassPathResource gradeResource = new ClassPathResource("test-grade/grade_report.xls");
        UserTakenCoursesList userTakenCoursesList = UserTakenCousrseParser.parseUserTakenCousrse(gradeResource.getFile());
        Integer studentId = UserTakenCousrseParser.getStudentId(gradeResource.getFile());
        GraduationRequirementStatus graduationRequirementStatus = new GraduationRequirementStatus();
        graduationRequirementStatus.checkGraduationRequirements(studentId, userTakenCoursesList, majorType);

        return graduationRequirementStatus;

    }

}
