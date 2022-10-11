package com.gist.graduation.requirment.domain.language;

import com.gist.graduation.requirment.domain.RequirementStatusBaseEntity;
import com.gist.graduation.requirment.domain.major.MajorType;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;

import java.util.List;
import java.util.stream.Collectors;

import static com.gist.graduation.requirment.domain.constants.LanguageBasicConstants.*;

public class LanguageBasic extends RequirementStatusBaseEntity {
    private static final int LANGUAGE_BASIC_MIN_CREDIT = 7;

    @Override
    public void checkRequirementByStudentId(Integer studentId, UserTakenCoursesList inputUserTakenCourses, MajorType majorType) {
        if (studentId >= 18) {
            checkEnglishFrom2018(inputUserTakenCourses);
            checkWritingFrom2018(inputUserTakenCourses);
        }

        if (this.getMessages().isEmpty()) {
            isSatisfied();
        }

        addCredit(this.getUserTakenCoursesList().sumCreditOfCourses());
        setMinConditionCredits(LANGUAGE_BASIC_MIN_CREDIT);
    }

    private void checkEnglishFrom2018(UserTakenCoursesList inputUserTakenCourses) {
        UserTakenCoursesList userTakenCourses = this.getUserTakenCoursesList();

        userTakenCourses.addAll(inputUserTakenCourses.getTakenCourses()
                .stream()
                .filter(s -> s.belongsToCourseInfosAny(List.of(ENGLISH_I, ENGLISH_II, ENGLISH_I_PRESENTATION)))
                .collect(Collectors.toList())
        );

        if (userTakenCourses.notExistAny(List.of(ENGLISH_I, ENGLISH_I_PRESENTATION))) {
            addMessage(String.format("%s 또는 %s을 수강해야 합니다.", ENGLISH_I, ENGLISH_I_PRESENTATION));
        }

        if (userTakenCourses.notExist(ENGLISH_II)) {
            addMessage(String.format("%s를 수강해야 합니다.", ENGLISH_II));
        }
    }

    private void checkWritingFrom2018(UserTakenCoursesList inputUserTakenCoursesList) {
        UserTakenCoursesList userTakenCourses = this.getUserTakenCoursesList();

        userTakenCourses.addAll(inputUserTakenCoursesList.getTakenCourses()
                .stream()
                .filter(s -> s.belongsToCourseInfosAny(WRITING_COURSE_INFOS))
                .collect(Collectors.toList())
        );

        if (userTakenCourses.notExistAny(WRITING_COURSE_INFOS)) {
            addMessage(String.format("%s 중 한 과목을 수강해야 합니다.", WRITING_COURSE_INFOS));
        }
    }


}
