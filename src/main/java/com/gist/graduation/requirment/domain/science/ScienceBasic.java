package com.gist.graduation.requirment.domain.science;


import com.gist.graduation.requirment.domain.RequirementStatusBaseEntity;
import com.gist.graduation.requirment.domain.major.MajorType;
import com.gist.graduation.user.taken_course.TakenCourse;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.gist.graduation.requirment.domain.constants.ScienceBasicConstants.Math.CALCULUS;
import static com.gist.graduation.requirment.domain.constants.ScienceBasicConstants.Math.CORE_MATH;
import static com.gist.graduation.requirment.domain.constants.ScienceBasicConstants.Science.COMPUTER_PROGRAMMING;

public class ScienceBasic extends RequirementStatusBaseEntity {

    public static final int MIN_CONDITION_CREDITS_WITH_COMPUTER_PROGRAMMING = 17;
    public static final int MIN_CONDITION_CREDITS = 18;

    @Override
    public void checkRequirementByStudentId(Integer studentId, UserTakenCoursesList inputUserTakenCoursesList, MajorType majorType) {
        if (studentId >= 18) {
            checkMathFrom2018(inputUserTakenCoursesList);
            checkScienceFrom2018(inputUserTakenCoursesList);
        }

        if (this.getMessages().isEmpty()) {
            isSatisfied();
        }

        addCredit(this.getUserTakenCoursesList().sumCreditOfCourses());
    }

    private void checkMathFrom2018(UserTakenCoursesList inputUserTakenCoursesList) {
        UserTakenCoursesList userTakenCoursesList = this.getUserTakenCoursesList();

        userTakenCoursesList.addAll(inputUserTakenCoursesList.getTakenCourses().stream()
                .filter(s -> s.belongsToCourseInfosAny(CALCULUS) || s.belongsToCourseInfosAny(CORE_MATH))
                .collect(Collectors.toList()));

        if (userTakenCoursesList.notExistAny(CALCULUS)) {
            this.getMessages().add(String.format("%s 중 한 과목을 수강해야 합니다.", CALCULUS));
        }

        if (userTakenCoursesList.notExistAny(CORE_MATH)) {
            this.getMessages().add(String.format("%s 중 한 과목을 수강해야 합니다.", CORE_MATH));
        }
    }

    private void checkScienceFrom2018(UserTakenCoursesList inputUserTakenCourses) {
        boolean isTakenComputerProgramming = checkComputerProgramming(inputUserTakenCourses);
        ScienceEnum.checkScienceBasicCourses(this, inputUserTakenCourses);

        ScienceVerifier scienceVerifier = ScienceEnum.ofScienceVerifier(inputUserTakenCourses);

        if(scienceVerifier.getCounts()<2){
            addMessage("기초 과학 과목을 추천해주기에는 과목수가 너무 적어요! 본인의 관심에 맞는 과목을 수강해주세요 :)");
            return;
        }

        scienceVerifier.checkTwoBlock(this, isTakenComputerProgramming);

        if (!isTakenComputerProgramming) {
            scienceVerifier.checkThreeBlock(this);
        }
    }

    private boolean checkComputerProgramming(UserTakenCoursesList inputUserTakenCourses) {
        UserTakenCoursesList userTakenCoursesList = this.getUserTakenCoursesList();

        userTakenCoursesList.addAll(inputUserTakenCourses.getTakenCourses().stream()
                .filter(s -> s.equalsCourseInfo(COMPUTER_PROGRAMMING))
                .collect(Collectors.toList())
        );

        if (userTakenCoursesList.contains(COMPUTER_PROGRAMMING)) {
            setMinConditionCredits(MIN_CONDITION_CREDITS_WITH_COMPUTER_PROGRAMMING);
            return true;
        }

        setMinConditionCredits(MIN_CONDITION_CREDITS);
        return false;
    }
}
