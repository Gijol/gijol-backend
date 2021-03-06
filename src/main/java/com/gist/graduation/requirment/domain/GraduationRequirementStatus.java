package com.gist.graduation.requirment.domain;

import com.gist.graduation.requirment.domain.etc.EtcMandatory;
import com.gist.graduation.requirment.domain.humanities.Humanities;
import com.gist.graduation.requirment.domain.language.LanguageBasic;
import com.gist.graduation.requirment.domain.major.Major;
import com.gist.graduation.requirment.domain.major.MajorType;
import com.gist.graduation.requirment.domain.other.EtcDuplication;
import com.gist.graduation.requirment.domain.other.OtherUncheckedClass;
import com.gist.graduation.requirment.domain.science.ScienceBasic;
import com.gist.graduation.user.taken_course.TakenCourse;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString
@RequiredArgsConstructor
public class GraduationRequirementStatus {

    public static final int TOTAL_CREDIT_CONDITION = 130;
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

    public void checkGraduationRequirements(Integer studentId, UserTakenCoursesList userTakenCoursesList, MajorType majorType) {
        EtcDuplication.checkDuplicate(userTakenCoursesList);
        setTotalCredits(userTakenCoursesList);
        isSatisfied();

        values().forEach(s -> s.checkRequirementByStudentId(studentId, userTakenCoursesList, majorType));
        otherUncheckedClass.checkRequirementByStudentId(studentId, userTakenCoursesList, this);
    }

    public List<TakenCourse> listOfExceptOtherUncheckedClasses() {
        return values().stream()
                .flatMap(s -> s.getUserTakenCoursesList().getTakenCourses().stream()).collect(Collectors.toList());
    }

    private void setTotalCredits(UserTakenCoursesList userTakenCoursesList) {
        this.totalCredits = userTakenCoursesList.sumCreditOfCourses();
    }

    private void isSatisfied() {
        this.satisfied = languageBasic.getSatisfied() && scienceBasic.getSatisfied() && major.getSatisfied() && humanities.getSatisfied()
                && etcMandatory.getSatisfied() && totalCredits >= TOTAL_CREDIT_CONDITION;

        if (totalCredits < TOTAL_CREDIT_CONDITION) {
            otherUncheckedClass.addMessage(String.format("?????? ????????? %d????????? ????????? ?????????.", TOTAL_CREDIT_CONDITION));
        }
    }

    private List<RequirementStatusBaseEntity> values() {
        return List.of(this.languageBasic, this.scienceBasic, this.major, this.humanities, this.etcMandatory);
    }
}
