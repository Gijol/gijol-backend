package com.gist.graduation.requirment.domain;
import com.gist.graduation.requirment.domain.language.LanguageBasic;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@ToString
public class GraduationRequirementStatus {

    private final LanguageBasic languageBasic;
//    private final ScienceBasic scienceBasic;
//    private final Major major;

    //todo add domain

    public GraduationRequirementStatus() {
        this.languageBasic = new LanguageBasic();
    }

    public GraduationRequirementStatus checkGraduationRequirements(Integer studentId, UserTakenCoursesList userTakenCoursesList) {
        languageBasic.checkRequirementByStudentId(studentId, userTakenCoursesList);

        return this;
    }
}
