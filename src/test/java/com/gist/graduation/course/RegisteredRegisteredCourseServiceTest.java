package com.gist.graduation.course;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class RegisteredRegisteredCourseServiceTest {

    @Autowired
    private RegisteredCourseService registeredCourseService;

    @Test
    void addCourseList() throws IOException {
        registeredCourseService.addCourseList();
    }

    @Test
    void getEnglishTest() {
        registeredCourseService.getLanguage();
    }


}