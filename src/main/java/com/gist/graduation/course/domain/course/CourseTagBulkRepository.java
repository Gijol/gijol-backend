package com.gist.graduation.course.domain.course;

import com.gist.graduation.course.domain.tag.CourseTag;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component(value = "CourseTagBulkRepository")
@RequiredArgsConstructor
public class CourseTagBulkRepository {

    private final JdbcTemplate jdbcTemplate;

    private final int batchSize = 1000;

    public void saveAllCourse(List<Course> courses) {
        final String sql = "INSERT INTO course(course_code, course_credit, course_name, prerequisite) VALUES (?, ?, ?, ?) ";
        jdbcTemplate.batchUpdate(sql, courses, batchSize, (preparedStatement, argument) -> {
            preparedStatement.setString(1, argument.getCourseInfo().getCourseCode());
            preparedStatement.setLong(2, argument.getCourseInfo().getCourseCredit());
            preparedStatement.setString(3, argument.getCourseInfo().getCourseName());
            preparedStatement.setString(4, argument.getPrerequisite());
        });
    }

    public void saveAllCourseTags(List<Course> courses) {
        final String sql = "insert into course_tag(course_id, course_tag_type) " +
                " values (?, ?)";

        final List<CourseTag> courseTags = courses.stream()
                .map(s -> s.getCourseTags().getCourseTags())
                .flatMap(List::stream)
                .collect(Collectors.toList());

        jdbcTemplate.batchUpdate(sql, courseTags, batchSize, (preparedStatement, argument) -> {
            preparedStatement.setLong(1, argument.getCourse().getId());
            preparedStatement.setString(2, argument.getCourseTagType().name());
        });
    }
}

