package com.gist.graduation.course;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseServiceTest {

    @Autowired
    private CourseService courseService;

    @Test
    void addCourseList() throws IOException {
        courseService.addCourseList();
    }

    @Test
    void getEnglishTest(){
        courseService.getLanguage();
    }


}