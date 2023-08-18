package com.gist.graduation.course.application;

import com.gist.graduation.course.application.description.DescriptionJsonDto;
import com.gist.graduation.course.application.description.DescriptionJsonParser;
import com.gist.graduation.course.domain.course.Course;
import com.gist.graduation.course.domain.course.CourseTagBulkRepository;
import com.gist.graduation.course.domain.course.CourseRepository;
import com.gist.graduation.course.domain.dto.CourseResponse;
import com.gist.graduation.course.domain.rawcourse.RawCourse;
import com.gist.graduation.utils.CourseListParser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseTagPolicy courseTagPolicy;
    private final CourseTagBulkRepository courseTagBulkRepository;

    public void createCourses(File file) {
        List<RawCourse> rawCourses = CourseListParser.parseToRawCourse(file);
        List<Course> courses = Course.listOf(rawCourses);

        List<DescriptionJsonDto> courseDescriptionFromJsonFile = DescriptionJsonParser.getCourseDescriptionFromJsonFile();
        for (DescriptionJsonDto descriptionJsonDto : courseDescriptionFromJsonFile) {
            courses.stream()
                    .filter(course -> course.getCourseInfo().getCourseCode().equals(descriptionJsonDto.getCourseCode()))
                    .forEach(course -> course.updateDescription(descriptionJsonDto.getCourseDescription()));
        }

        List<Course> savedCourses = courseRepository.saveAll(courses);
        courseTagPolicy.tagAllCourses(savedCourses);
        courseTagBulkRepository.saveAllCourseTags(savedCourses);
    }

    @Transactional(readOnly = true)
    public List<CourseResponse> findByMinor(String code, Pageable pageable) {
        if (code.equalsIgnoreCase("NONE")) {
            return CourseResponse.listOf(courseRepository.findAll(pageable).toList());
        }
        return CourseResponse.listOf(courseRepository.findCoursesByCourseCode(pageable, code));

    }
}
