package com.gist.graduation.course.application;

import com.gist.graduation.course.application.description.DescriptionJsonDto;
import com.gist.graduation.course.application.description.DescriptionJsonParser;
import com.gist.graduation.course.domain.course.Course;
import com.gist.graduation.course.domain.course.CourseBulkRepository;
import com.gist.graduation.course.domain.course.CourseRepository;
import com.gist.graduation.course.domain.dto.CourseHistoriesResponse;
import com.gist.graduation.course.domain.dto.CourseResponse;
import com.gist.graduation.course.domain.rawcourse.RawCourse;
import com.gist.graduation.course.domain.rawcourse.RawCourseRepository;
import com.gist.graduation.course.domain.tag.CourseTag;
import com.gist.graduation.course.domain.tag.CourseTagRepository;
import com.gist.graduation.course.domain.tag.CourseTagType;
import com.gist.graduation.utils.CourseListParser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseTagPolicy courseTagPolicy;
    private final CourseBulkRepository courseBulkRepository;
    private final RawCourseRepository rawCourseRepository;
    private final CourseTagRepository courseTagRepository;

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

        setCourseIdToRawCourse(rawCourses, savedCourses);
        courseTagPolicy.tagAllCourses(savedCourses);
        courseBulkRepository.saveAllCourseTags(savedCourses);
        courseBulkRepository.saveAllRawCourses(rawCourses);
    }

    private void setCourseIdToRawCourse(List<RawCourse> rawCourses, List<Course> savedCourses) {
        for (RawCourse rawCourse : rawCourses) {
            savedCourses.stream()
                    .filter(course -> course.belongToCourseInfo(List.of(rawCourse.getCourseInfo())))
                    .forEach(course -> rawCourse.setCourseId(course.getId()));
        }
    }

    @Transactional(readOnly = true)
    public Page<CourseResponse> findByCourseSearchCode(String code, String courseSearchString, Pageable pageable) {
        CourseTagType courseType = CourseTagType.containsStringIgnoreCase(code);
        if (Objects.nonNull(courseType)) {
            Page<CourseTag> courseTagsByCourseTagType = courseTagRepository.findCourseTagsByCourseTagType(courseType, courseSearchString, pageable);
            List<Course> courses = courseTagsByCourseTagType.stream()
                    .map(CourseTag::getCourse)
                    .collect(Collectors.toList());

            return new PageImpl<>(CourseResponse.listOf(courses), pageable, courseTagsByCourseTagType.getTotalElements());
        }

        if (code.equalsIgnoreCase("NONE")) {
            Page<Course> courses = courseRepository.findAllBySearchString(pageable, courseSearchString);
            return new PageImpl<>(CourseResponse.listOf(courses.toList()), pageable, courses.getTotalElements());
        }
        Page<Course> coursesByCourseCode = courseRepository.findCoursesByCourseCode(pageable, code, courseSearchString);
        return new PageImpl<>(CourseResponse.listOf(coursesByCourseCode.toList()), pageable, coursesByCourseCode.getTotalElements());

    }

    public CourseHistoriesResponse findCourseHistoriesByCourseId(Long courseId) {
        List<RawCourse> rawCourses = rawCourseRepository.findAllByCourseId(courseId);
        Collections.reverse(rawCourses);
        return CourseHistoriesResponse.from(rawCourses);
    }
}
