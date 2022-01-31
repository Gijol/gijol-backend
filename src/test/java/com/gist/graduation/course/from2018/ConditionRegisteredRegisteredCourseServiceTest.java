package com.gist.graduation.course.from2018;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ConditionRegisteredRegisteredCourseServiceTest {

    @Autowired
    private ConditionCourseService conditionCourseService;

    @Test
    void getLanguage() {
        conditionCourseService.getLanguage();
    }
}