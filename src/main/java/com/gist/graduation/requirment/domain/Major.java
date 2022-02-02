package com.gist.graduation.requirment.domain;

import com.gist.graduation.user.taken_course.TakenCourse;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Document
public class Major extends RequirementStatusBaseEntity{

    public void checkRequirementByStudentId(Integer studentId, UserTakenCoursesList inputUserTakenCoursesList, MajorType majorType){
        if (studentId >= 18) {
            checkMajorFrom2018(inputUserTakenCoursesList, majorType);
        }

        if (getMessage().isEmpty()) {
            isSatisfied();
        }

        addCredit(this.getUserTakenCoursesList().sumCreditOfCourses());
    }

    private void checkMajorFrom2018(UserTakenCoursesList inputUserTakenCoursesList, MajorType majorType) {
        checkMandatoryMajor(inputUserTakenCoursesList, majorType);
        checkElectiveMajor(inputUserTakenCoursesList, majorType);
        System.out.println(majorType.getMandatoryMajorCourse().toString());
    }

    private void checkMandatoryMajor(UserTakenCoursesList inputUserTakenCoursesList, MajorType majorType) {
        List<TakenCourse> lackMajorMandatoryCourse = MajorType.getLackMajorMandatoryCourse(inputUserTakenCoursesList, majorType);
        for (TakenCourse toTakeCourse : lackMajorMandatoryCourse) {
            getMessage().add(String.format("%s를 수강해야 합니다.", toTakeCourse));
        }
        getUserTakenCoursesList().addAll(MajorType.getUserTakenMajorCourses(inputUserTakenCoursesList, majorType));
    }

    private void checkElectiveMajor(UserTakenCoursesList inputUserTakenCoursesList, MajorType majorType) {
        getUserTakenCoursesList().addAll(inputUserTakenCoursesList.getTakenCourses()
                .stream()
                .filter(s -> !majorType.getMandatoryMajorCourse().contains(s))
                .filter(s -> s.getCourseCode().contains(majorType.name()))
                .collect(Collectors.toList()));
    }

}
