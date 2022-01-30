package com.gist.graduation.course;

import com.gist.graduation.utils.CourseListParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseListParser courseListParser;
    private final CourseRepository courseRepository;

    public void addCourseList() throws IOException {
        List<Course> courseList = courseListParser.getCourseList();
        for (Course course : courseList) {
            Course savedCourse = courseRepository.save(course);
            System.out.println(savedCourse);
        }
    }

    public void getLanguage() {
        List<Course> courseList = courseRepository.findAll();
        for (Course course : courseList) {
            List<CoreCourseInfo> english = course.getCoreCourseInfos().stream()
                    .filter(s -> s.getName().contains("영어 Ⅰ"))
                    .filter(s -> s.getCode().contains("GS"))
                    .filter(s -> s.getCredit() == 2)
                    .collect(Collectors.toList());
            System.out.println(String.valueOf(course.getYear())+ course.getSemester() + english);
        }
    }

}
