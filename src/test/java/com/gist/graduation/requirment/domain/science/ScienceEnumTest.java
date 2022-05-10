package com.gist.graduation.requirment.domain.science;

import com.gist.graduation.requirment.UserConditionTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScienceEnumTest extends UserConditionTest {

    @Test
    void test(){
        ScienceVerifier scienceVerifier = ScienceEnum.ofScienceVerifier(takenCourses);
        System.out.println(scienceVerifier);
    }

}