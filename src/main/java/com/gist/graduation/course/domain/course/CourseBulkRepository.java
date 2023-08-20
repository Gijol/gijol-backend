package com.gist.graduation.course.domain.course;

import com.gist.graduation.course.domain.rawcourse.RawCourse;
import com.gist.graduation.course.domain.tag.CourseTag;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Component(value = "CourseBulkRepository")
@RequiredArgsConstructor
public class CourseBulkRepository {

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
        final String sql = "insert into course_tag(course_id, course_tag_type, created_at, updated_at) " +
                " values (?, ?, ?, ?)";

        final List<CourseTag> courseTags = courses.stream()
                .map(s -> s.getCourseTags().getCourseTags())
                .flatMap(List::stream)
                .collect(Collectors.toList());

        jdbcTemplate.batchUpdate(sql, courseTags, batchSize, (preparedStatement, argument) -> {
            preparedStatement.setLong(1, argument.getCourse().getId());
            preparedStatement.setString(2, argument.getCourseTagType().name());
            preparedStatement.setTimestamp(3, Timestamp.from(Instant.now()));
            preparedStatement.setTimestamp(4, Timestamp.from(Instant.now()));
        });
    }

    public void saveAllRawCourses(List<RawCourse> courses) {
        final String sql = "insert into raw_course(created_at, updated_at, course_code, course_credit, course_name, course_professor, course_room, course_time, course_type, prerequisite, semester, year, course_id)" +
                " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.batchUpdate(sql, courses, batchSize, (preparedStatement, argument) -> {
            preparedStatement.setTimestamp(1, Timestamp.from(Instant.now()));
            preparedStatement.setTimestamp(2, Timestamp.from(Instant.now()));
            preparedStatement.setString(3, argument.getCourseInfo().getCourseCode());
            preparedStatement.setLong(4, argument.getCourseInfo().getCourseCredit());
            preparedStatement.setString(5, argument.getCourseInfo().getCourseName());
            preparedStatement.setString(6, argument.getCourseProfessor());
            preparedStatement.setString(7, argument.getCourseRoom());
            preparedStatement.setString(8, argument.getCourseTime());
            preparedStatement.setString(9, argument.getCourseType());
            preparedStatement.setString(10, argument.getPrerequisite());
            preparedStatement.setString(11, argument.getSemesterInfo().getSemester());
            preparedStatement.setInt(12, argument.getSemesterInfo().getYear());
            preparedStatement.setLong(13, argument.getCourseId() == null ? 0L : argument.getCourseId());
        });
    }
}

