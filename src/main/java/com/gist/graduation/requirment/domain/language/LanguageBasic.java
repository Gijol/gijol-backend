package com.gist.graduation.requirment.domain.language;

import com.gist.graduation.requirment.domain.RequirementStatusBaseEntity;
import com.gist.graduation.requirment.domain.major.MajorType;
import com.gist.graduation.user.taken_course.TakenCourse;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;

import java.util.List;
import java.util.stream.Collectors;

import static com.gist.graduation.requirment.domain.constants.LanguageBasicConstants.*;

public class LanguageBasic extends RequirementStatusBaseEntity {
    private static final int LANGUAGE_BASIC_MIN_CREDIT = 7;

    @Override
    public void checkRequirementByStudentId(Integer studentId, UserTakenCoursesList inputUserTakenCoursesList, MajorType majorType) {
        if (studentId >= 18) {
            checkEnglishFrom2018(inputUserTakenCoursesList);
            checkWritingFrom2018(inputUserTakenCoursesList);
        }

        if (this.getMessages().isEmpty()) {
            isSatisfied();
        }

        addCredit(this.getUserTakenCoursesList().sumCreditOfCourses());
        setMinConditionCredits(LANGUAGE_BASIC_MIN_CREDIT);
    }

    private void checkEnglishFrom2018(UserTakenCoursesList inputUserTakenCoursesList) {
        UserTakenCoursesList userTakenCoursesList = this.getUserTakenCoursesList();

        userTakenCoursesList.addAll(inputUserTakenCoursesList.getTakenCourses()
                .stream()
                .filter(s -> s.equals(ENGLISH_I) || s.equals(ENGLISH_I_PRESENTATION) || s.equals(ENGLISH_II))
                .collect(Collectors.toList())
        );

        if (userTakenCoursesList.notExist(ENGLISH_I) && userTakenCoursesList.notExist(ENGLISH_I_PRESENTATION)) {
            this.getMessages().add(String.format("%s 또는 %s을 수강해야 합니다.", ENGLISH_I, ENGLISH_I_PRESENTATION));
        }

        if (inputUserTakenCoursesList.notExist(ENGLISH_II)) {
            this.getMessages().add(String.format("%s를 수강해야 합니다.", ENGLISH_II));
        }
    }

    private void checkWritingFrom2018(UserTakenCoursesList inputUserTakenCoursesList) {
        List<TakenCourse> userTakenCourses = inputUserTakenCoursesList.getTakenCourses()
                .stream()
                .filter(WRITING_COURSE_LIST::contains)
                .collect(Collectors.toList());

        if (userTakenCourses.isEmpty()) {
            this.getMessages().add(String.format("%s 중 한 과목을 수강해야 합니다.", WRITING_COURSE_LIST));
        }

        getUserTakenCoursesList().addAll(userTakenCourses);
    }


}
