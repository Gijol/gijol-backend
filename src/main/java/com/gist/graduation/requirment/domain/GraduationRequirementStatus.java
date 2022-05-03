package com.gist.graduation.requirment.domain;

import com.gist.graduation.requirment.domain.etc.EtcMandatory;
import com.gist.graduation.requirment.domain.humanities.Humanities;
import com.gist.graduation.requirment.domain.language.LanguageBasic;
import com.gist.graduation.requirment.domain.science.ScienceBasic;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class GraduationRequirementStatus {

    private final LanguageBasic languageBasic;
    private final ScienceBasic scienceBasic;
    private final Major major;
    private final Humanities humanities;
    private final EtcMandatory etcMandatory;

    //todo add domain


    public GraduationRequirementStatus() {
        this.languageBasic = new LanguageBasic();
        this.scienceBasic = new ScienceBasic();
        this.major = new Major();
        this.humanities = new Humanities();
        this.etcMandatory = new EtcMandatory();
    }

    public GraduationRequirementStatus checkGraduationRequirements(Integer studentId, UserTakenCoursesList userTakenCoursesList, MajorType majorType) {
        languageBasic.checkRequirementByStudentId(studentId, userTakenCoursesList);
        scienceBasic.checkRequirementByStudentId(studentId, userTakenCoursesList);
        major.checkRequirementByStudentId(studentId, userTakenCoursesList, majorType);
        humanities.checkRequirementByStudentId(studentId, userTakenCoursesList);
        etcMandatory.checkRequirementByStudentId(studentId, userTakenCoursesList);
        return this;
    }
}
