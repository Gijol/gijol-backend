package com.gist.graduation.requirment.domain;

import com.gist.graduation.requirment.domain.etc.EtcMandatory;
import com.gist.graduation.requirment.domain.humanities.Humanities;
import com.gist.graduation.requirment.domain.language.LanguageBasic;
import com.gist.graduation.requirment.domain.major.Major;
import com.gist.graduation.requirment.domain.other.OtherUncheckedClass;
import com.gist.graduation.requirment.domain.science.ScienceBasic;
import com.gist.graduation.user.taken_course.TakenCourse;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@RequiredArgsConstructor
public class GraduationRequirementStatus {

    private final LanguageBasic languageBasic;
    private final ScienceBasic scienceBasic;
    private final Major major;
    private final Humanities humanities;
    private final EtcMandatory etcMandatory;
    private final OtherUncheckedClass otherUncheckedClass;
    private Integer totalCredits;
    private Boolean satisfied;


    public GraduationRequirementStatus() {
        this.languageBasic = new LanguageBasic();
        this.scienceBasic = new ScienceBasic();
        this.major = new Major();
        this.humanities = new Humanities();
        this.etcMandatory = new EtcMandatory();
        this.otherUncheckedClass = new OtherUncheckedClass();
    }

    public GraduationRequirementStatus checkGraduationRequirements(Integer studentId, UserTakenCoursesList userTakenCoursesList, MajorType majorType) {
        languageBasic.checkRequirementByStudentId(studentId, userTakenCoursesList);
        scienceBasic.checkRequirementByStudentId(studentId, userTakenCoursesList);
        major.checkRequirementByStudentId(studentId, userTakenCoursesList, majorType);
        humanities.checkRequirementByStudentId(studentId, userTakenCoursesList);
        etcMandatory.checkRequirementByStudentId(studentId, userTakenCoursesList);
        otherUncheckedClass.checkRequirementByStudentId(studentId, userTakenCoursesList, this);
        return this;
    }


    public List<TakenCourse> listOfExceptOtherUncheckedClasses(){
        List<TakenCourse> userTakenCourses = new ArrayList<>();
        userTakenCourses.addAll(this.languageBasic.getUserTakenCoursesList().getTakenCourses());
        userTakenCourses.addAll(this.scienceBasic.getUserTakenCoursesList().getTakenCourses());
        userTakenCourses.addAll(this.major.getUserTakenCoursesList().getTakenCourses());
        userTakenCourses.addAll(this.humanities.getUserTakenCoursesList().getTakenCourses());
        userTakenCourses.addAll(this.etcMandatory.getUserTakenCoursesList().getTakenCourses());
        return userTakenCourses;
    }
}
