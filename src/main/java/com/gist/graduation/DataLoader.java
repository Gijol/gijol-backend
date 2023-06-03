package com.gist.graduation;

import com.gist.graduation.course.application.CourseService;
import com.gist.graduation.course.application.RawCourseService;
import com.gist.graduation.utils.FileResourceUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.File;

@Profile("local | dev")
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final FileResourceUtils fileResourceUtils;
    private final RawCourseService rawCourseService;

    private final CourseService courseService;

    @Override
    public void run(String... args) throws Exception {
        File file = fileResourceUtils.convertPathResourceToCourseListFileAtServer();
        courseService.createCourses(file);
//        rawCourseService.saveRawCourses(file);
    }
}
