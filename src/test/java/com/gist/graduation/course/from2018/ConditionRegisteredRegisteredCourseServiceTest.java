package com.gist.graduation.course.from2018;

import com.gist.graduation.requirment.domain.GraduationRequirementStatusRepository;
import com.gist.graduation.utils.UserTakenCousrseParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ConditionRegisteredRegisteredCourseServiceTest {

    @Autowired
    private UserTakenCousrseParser userTakenCousrseParser;

    @Autowired
    private GraduationRequirementStatusRepository graduationRequirementStatusRepository;


}