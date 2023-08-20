package com.gist.graduation.requirment.domain.humanities;

import com.gist.graduation.course.domain.CourseInfo;
import com.gist.graduation.requirment.domain.RequirementStatusBaseEntity;
import com.gist.graduation.requirment.domain.major.MajorType;
import com.gist.graduation.user.taken_course.CourseType;
import com.gist.graduation.user.taken_course.TakenCourse;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;
import com.gist.graduation.utils.HumanitiesListParser;

import java.util.List;
import java.util.stream.Collectors;

public class Humanities extends RequirementStatusBaseEntity {

    public static final Integer HUMANITIES_MIN_CREDIT = 24;
    public static final int MAXIMUM_CREDITS = 36;

    @Override
    public void checkRequirementByStudentId(Integer studentId, UserTakenCoursesList inputUserTakenCoursesList, MajorType majorType) {
        if (studentId >= 18) {
            checkMandatoryHumanities(inputUserTakenCoursesList);
            checkOtherHumanitiesCredit(inputUserTakenCoursesList);
        }

        if (this.getMessages().isEmpty()) {
            isSatisfied();
        }

        addCredit(Math.min(this.getUserTakenCoursesList().sumCreditOfCourses(), MAXIMUM_CREDITS));
        setMinConditionCredits(HUMANITIES_MIN_CREDIT);
    }

    private void checkMandatoryHumanities(UserTakenCoursesList inputUserTakenCoursesList) {
        checkHUSMandatory(inputUserTakenCoursesList);
        checkPPEMandatory(inputUserTakenCoursesList);
    }

    private void checkHUSMandatory(UserTakenCoursesList inputUserTakenCoursesList) {
        UserTakenCoursesList userTakenCourses = this.getUserTakenCoursesList();
        List<CourseInfo> HUSCoursesList = HumanitiesListParser.getHUSCoursesList();


        userTakenCourses.addAll(
                inputUserTakenCoursesList.getTakenCourses().stream()
                        .filter(s -> s.belongsToCourseInfosAny(HUSCoursesList))
                        .map(s -> s.setCourseTypeTo(CourseType.HUS))
                        .collect(Collectors.toList())
        );


        int husMinimumCondition = 6 - getSumofCredits(userTakenCourses.findCoursesByType(CourseType.HUS));

        if (husMinimumCondition > 0) {
            addMessage(String.format("HUS 과목을 %d학점 더 들어야 합니다.", (husMinimumCondition)));
        }
    }

    private void checkPPEMandatory(UserTakenCoursesList inputUserTakenCoursesList) {
        UserTakenCoursesList userTakenCourses = this.getUserTakenCoursesList();
        List<CourseInfo> PPECoursesList = HumanitiesListParser.getPPECoursesList();

        userTakenCourses.addAll(
                inputUserTakenCoursesList.getTakenCourses().stream()
                        .filter(s -> s.belongsToCourseInfosAny(PPECoursesList))
                        .map(s -> s.setCourseTypeTo(CourseType.PPE))
                        .collect(Collectors.toList())
        );


        int ppeMinimumCondition = 6 - getSumofCredits(userTakenCourses.findCoursesByType(CourseType.PPE));

        if (ppeMinimumCondition > 0) {
            addMessage(String.format("PPE 과목을 %d학점 더 들어야 합니다.", (ppeMinimumCondition)));
        }
    }

    private void checkOtherHumanitiesCredit(UserTakenCoursesList inputUserTakenCoursesList) {
        UserTakenCoursesList userTakenCourses = this.getUserTakenCoursesList();
        List<CourseInfo> humanitiesCourseList = HumanitiesListParser.getHumanitiesCoursesList();

        userTakenCourses.addAll(
                inputUserTakenCoursesList.getTakenCourses().stream()
                        .filter(s -> s.belongsToCourseInfosAny(humanitiesCourseList))
                        .filter(s -> !CourseType.HUS.equals(s.getCourseType()) && !CourseType.PPE.equals(s.getCourseType()))
                        .collect(Collectors.toList())
        );

        int humanitiesMinimumCondition = HUMANITIES_MIN_CREDIT - userTakenCourses.sumCreditOfCourses();

        if (humanitiesMinimumCondition > 0) {
            addMessage(String.format("인문사회 과목을 %d학점 더 들어야 합니다.", (humanitiesMinimumCondition)));
        }
    }


    private int getSumofCredits(List<TakenCourse> takenCourses) {
        return takenCourses.stream()
                .mapToInt(TakenCourse::getCredit)
                .sum();
    }


}
