package com.gist.graduation.requirment.domain.science;


import com.gist.graduation.requirment.domain.RequirementStatusBaseEntity;
import com.gist.graduation.requirment.domain.major.MajorType;
import com.gist.graduation.user.taken_course.TakenCourse;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;

import java.util.List;
import java.util.stream.Collectors;

import static com.gist.graduation.requirment.domain.constants.ScienceBasicConstant.Math.CALCULUS;
import static com.gist.graduation.requirment.domain.constants.ScienceBasicConstant.Math.CORE_MATH;
import static com.gist.graduation.requirment.domain.constants.ScienceBasicConstant.Science.COMPUTER_PROGRAMMING;

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

        List<TakenCourse> userTakenMathCourses = inputUserTakenCoursesList.getTakenCourses()
                .stream()
                .filter(s -> CALCULUS.contains(s) || CORE_MATH.contains(s))
                .collect(Collectors.toList());

        if (userTakenMathCourses.stream().noneMatch(CALCULUS::contains)) {
            this.getMessages().add(String.format("%s 중 한 과목을 수강해야 합니다.", CALCULUS));
        }

        if (userTakenMathCourses.stream().noneMatch(CORE_MATH::contains)) {
            this.getMessages().add(String.format("%s 중 한 과목을 수강해야 합니다.", CORE_MATH));
        }

        getUserTakenCoursesList().addAll(userTakenMathCourses);
    }

    private void checkScienceFrom2018(UserTakenCoursesList userTakenCoursesList) {
        boolean tookComputer = checkComputerProgramming(userTakenCoursesList);
        ScienceEnum.checkScienceBasicCourses(this, userTakenCoursesList);

        ScienceVerifier scienceVerifier = ScienceEnum.ofScienceVerifier(userTakenCoursesList);
        scienceVerifier.checkTwoBlock(this, tookComputer);

        if (!tookComputer) {
            scienceVerifier.checkThreeBlock(this);
        }
    }

    private boolean checkComputerProgramming(UserTakenCoursesList userTakenCoursesList) {
        if (userTakenCoursesList.contains(COMPUTER_PROGRAMMING)) {
            this.getUserTakenCoursesList().getTakenCourses().add(COMPUTER_PROGRAMMING);
            setMinConditionCredits(MIN_CONDITION_CREDITS_WITH_COMPUTER_PROGRAMMING);
            return true;
        }
        setMinConditionCredits(MIN_CONDITION_CREDITS);
        return false;
    }

}
