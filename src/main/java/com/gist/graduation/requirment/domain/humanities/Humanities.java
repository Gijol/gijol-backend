package com.gist.graduation.requirment.domain.humanities;

import com.gist.graduation.requirment.domain.RequirementStatusBaseEntity;
import com.gist.graduation.user.taken_course.TakenCourse;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;
import com.gist.graduation.utils.HumanitiesListParser;

import java.util.List;
import java.util.stream.Collectors;

public class Humanities extends RequirementStatusBaseEntity {

    public void checkRequirementByStudentId(Integer studentId, UserTakenCoursesList inputUserTakenCoursesList) {
        if (studentId >= 18) {

        }

        if (this.getMessages().isEmpty()) {
            isSatisfied();
        }

        addCredit(this.getUserTakenCoursesList().sumCreditOfCourses());
    }

    private void checkMandatoryHumanities(UserTakenCoursesList inputUserTakenCoursesList) {
        checkHUSMandatory(inputUserTakenCoursesList);
        checkPPEMandatory(inputUserTakenCoursesList);

    }

    private void checkHUSMandatory(UserTakenCoursesList inputUserTakenCoursesList) {
        List<TakenCourse> husCoursesList = HumanitiesListParser.getHUSCoursesList();
        List<TakenCourse> userTakneHUSCourses = inputUserTakenCoursesList.getTakenCourses().stream()
                .filter(husCoursesList::contains)
                .collect(Collectors.toList());

        int husMinimumCondition = 12 - getSumofCredits(userTakneHUSCourses);
        if ( husMinimumCondition > 0) {
            addMessage(String.format("HUS 과목을 %d학점 더 들어야 합니다.", (husMinimumCondition)));
        }

        this.getUserTakenCoursesList().addAll(
                userTakneHUSCourses
        );
    }

    private void checkPPEMandatory(UserTakenCoursesList inputUserTakenCoursesList) {
        List<TakenCourse> PPECoursesList = HumanitiesListParser.getPPECoursesList();
        List<TakenCourse> userTaknePPECourses = inputUserTakenCoursesList.getTakenCourses().stream()
                .filter(PPECoursesList::contains)
                .collect(Collectors.toList());

        int ppeMinimumCondition = 12 - getSumofCredits(userTaknePPECourses);
        if ( ppeMinimumCondition > 0) {
            addMessage(String.format("PPE 과목을 %d학점 더 들어야 합니다.", (ppeMinimumCondition)));
        }

        this.getUserTakenCoursesList().addAll(
                userTaknePPECourses
        );
    }

    private void checkOtherHumanitiesCredit(UserTakenCoursesList inputUserTakenCoursesList) {
        List<TakenCourse> humanitiesCourseList = HumanitiesListParser.getHumanitiesCoursesList();
        List<TakenCourse> userTakenHumanitiesCourses = inputUserTakenCoursesList.getTakenCourses().stream()
                .filter(humanitiesCourseList::contains)
                .filter(s -> !this.getUserTakenCoursesList().contains(s))
                .collect(Collectors.toList());

        //todo add credits

    }


    private int getSumofCredits(List<TakenCourse> takenCourses) {
        return takenCourses.stream()
                .mapToInt(TakenCourse::getCredit)
                .sum();
    }


}