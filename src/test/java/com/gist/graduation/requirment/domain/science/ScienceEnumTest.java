package com.gist.graduation.requirment.domain.science;

import com.gist.graduation.requirment.UserConditionTest;
import org.junit.jupiter.api.Test;

class ScienceEnumTest extends UserConditionTest {

    @Test
    void test() {
        ScienceVerifier scienceVerifier = ScienceEnum.ofScienceVerifier(takenCourses);
        System.out.println(scienceVerifier);
    }

    @Test
    void checkTwoBlock() {
        ScienceVerifier scienceVerifier = ScienceEnum.ofScienceVerifier(takenCourses);
        ScienceBasic scienceBasic = new ScienceBasic();
        scienceBasic.checkRequirementByStudentId(20, takenCourses);
        System.out.println(scienceBasic);
    }

}