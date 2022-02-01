//package com.gist.graduation.course;
//
//import com.gist.graduation.utils.CourseListParser;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.validation.annotation.Validated;
//
//import java.io.IOException;
//import java.util.List;
//
//@SpringBootTest
//class RegisteredRegisteredCourseServiceTest {
//
//    @Autowired
//    private RegisteredCourseService registeredCourseService;
//
//    @Autowired
//    private RegisteredCourseRepository registeredCourseRepository;
//
//    @Autowired
//    private CourseListParser courseListParser;
//
//    @BeforeEach
//    void setup() throws IOException {
//        List<RegisteredCourse> courseList = courseListParser.getCourseList();
////        for (RegisteredCourse registeredCourse : courseList) {
////            RegisteredCourse savedRegisteredCourse = registeredCourseRepository.save(registeredCourse);
////            System.out.println(savedRegisteredCourse);
////        }
//    }
//
//    @Test
//    void getEnglishTest() throws IOException {
//        System.out.println(courseListParser.getCourseList());
//        registeredCourseService.getLanguage();
//    }
//
//
//
//
//    @AfterEach
//    void tearDown() {
//        registeredCourseRepository.deleteAll();
//    }
//
//
//}