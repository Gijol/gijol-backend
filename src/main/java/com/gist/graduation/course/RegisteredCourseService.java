package com.gist.graduation.course;

import com.gist.graduation.utils.CourseListParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class RegisteredCourseService {

    private final CourseListParser courseListParser;
    private final RegisteredCourseRepository registeredCourseRepository;

    public void addCourseList() throws IOException {
        List<RegisteredCourse> registeredCourseList = courseListParser.getCourseList();
        for (RegisteredCourse registeredCourse : registeredCourseList) {
            RegisteredCourse savedRegisteredCourse = registeredCourseRepository.save(registeredCourse);
            System.out.println(savedRegisteredCourse);
        }
    }

    public void getLanguage() {
        List<RegisteredCourse> registeredCourseList = registeredCourseRepository.findAll();
        for (RegisteredCourse registeredCourse : registeredCourseList) {
            List<Course> english = registeredCourse.getCourses().stream()
                    .filter(s -> s.getName().contains("영어 Ⅰ"))
                    .filter(s -> s.getCode().contains("GS"))
                    .filter(s -> s.getCredit() == 2)
                    .collect(Collectors.toList());
            System.out.println(registeredCourse.getYear() + registeredCourse.getSemester() + english);
        }
    }

}
