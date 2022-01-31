package com.gist.graduation.utils;

import com.gist.graduation.course.Course;
import com.gist.graduation.course.RegisteredCourse;
import com.gist.graduation.course.RegisteredCourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CourseListPrinter {

    private final RegisteredCourseRepository registeredCourseRepository;

    private Set<Course> getCoreEnglishCourses(List<RegisteredCourse> registeredCourseList) {
        Set<Course> englishConditionCourse = new HashSet<>();
        for (RegisteredCourse registeredCourse : registeredCourseList) {
            List<Course> registeredEnglishCourses = registeredCourse.getCourses().stream()
                    .filter(s -> s.getName().contains("영어 Ⅰ") || s.getName().contains("영어 II"))
                    .filter(s -> s.getCode().contains("GS1") || s.getCode().contains("GS2"))
                    .filter(s -> s.getCredit() == 2)
                    .collect(Collectors.toList());
            englishConditionCourse.addAll(registeredEnglishCourses);
        }
        return englishConditionCourse;
    }

    private Set<Course> getCoreWritingClass(List<RegisteredCourse> registeredCourseList) {
        Set<Course> writingConditionCourse = new HashSet<>();
        for (RegisteredCourse registeredCourse : registeredCourseList) {
            List<Course> registeredEnglishCourses = registeredCourse.getCourses().stream()
                    .filter(s -> s.getName().contains("글쓰기"))
                    .filter(s -> s.getCredit() == 3)
                    .collect(Collectors.toList());
            writingConditionCourse.addAll(registeredEnglishCourses);
        }
        return writingConditionCourse;
    }


}
