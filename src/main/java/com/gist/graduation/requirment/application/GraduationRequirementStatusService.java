package com.gist.graduation.requirment.application;

import com.gist.graduation.requirment.domain.GraduationRequirementStatus;
import com.gist.graduation.requirment.domain.major.MajorType;
import com.gist.graduation.requirment.dto.DemoGradeToCheckRequest;
import com.gist.graduation.requirment.dto.GradeToCheckRequest;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;
import com.gist.graduation.utils.UserTakenCousrseParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
@Slf4j
public class GraduationRequirementStatusService {

    public GraduationRequirementStatus checkGraduationCondition(GradeToCheckRequest request) throws IOException {
        File file = multiPartToFile(request.getMultipartFile());
        UserTakenCoursesList userTakenCoursesList = UserTakenCousrseParser.parseUserTakenCourse(file);
        log.info(userTakenCoursesList.toString());
        Integer studentId = UserTakenCousrseParser.getStudentId(file);
        GraduationRequirementStatus graduationRequirementStatus = new GraduationRequirementStatus();
        graduationRequirementStatus.checkGraduationRequirements(studentId, userTakenCoursesList, request.getMajorType());
        log.info(graduationRequirementStatus.toString());
        return graduationRequirementStatus;
    }

    public GraduationRequirementStatus checkGraduationConditionForDemo(DemoGradeToCheckRequest request) throws IOException {
        File file = multiPartToFile(request.getMultipartFile());
        UserTakenCoursesList userTakenCoursesList = UserTakenCousrseParser.parseUserTakenCourse(file);
        log.info(userTakenCoursesList.toString());
        Integer studentId = UserTakenCousrseParser.getStudentId(file);
        GraduationRequirementStatus graduationRequirementStatus = new GraduationRequirementStatus();
        graduationRequirementStatus.checkGraduationRequirements(studentId, userTakenCoursesList, request.getMajorType(), request.getMinorType());
        log.info(graduationRequirementStatus.toString());
        return graduationRequirementStatus;
    }


    private File multiPartToFile(MultipartFile multipartFile) throws IOException {
        InputStream inputStream = multipartFile.getInputStream();
        File file = File.createTempFile(UUID.randomUUID().toString(), ".xlsx");
        FileUtils.copyToFile(inputStream, file);
        return file;
    }

    public GraduationRequirementStatus checkGradeTest(File file, MajorType majorType) throws IOException {
        UserTakenCoursesList userTakenCoursesList = UserTakenCousrseParser.parseUserTakenCourse(file);
        Integer studentId = UserTakenCousrseParser.getStudentId(file);
        GraduationRequirementStatus graduationRequirementStatus = new GraduationRequirementStatus();
        graduationRequirementStatus.checkGraduationRequirements(studentId, userTakenCoursesList, majorType);
        return graduationRequirementStatus;
    }

}
