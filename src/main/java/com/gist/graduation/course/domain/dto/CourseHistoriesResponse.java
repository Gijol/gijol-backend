package com.gist.graduation.course.domain.dto;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.gist.graduation.course.domain.course.vo.SemesterInfo;
import com.gist.graduation.course.domain.rawcourse.RawCourse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CourseHistoriesResponse {

    List<CourseHistoryResponse> courseHistoryResponses;

    public static CourseHistoriesResponse from(List<RawCourse> rawCourses) {
        return new CourseHistoriesResponse(rawCourses.stream()
                .map(CourseHistoryResponse::from)
                .collect(Collectors.toList())
        );
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class CourseHistoryResponse {

        @JsonUnwrapped
        private SemesterInfo semesterInfo;
        private String courseProfessor;
        private String courseTime;
        private String courseRoom;

        public static CourseHistoryResponse from(RawCourse rawCourse) {
            return new CourseHistoryResponse(rawCourse.getSemesterInfo(), rawCourse.getCourseProfessor(), rawCourse.getCourseTime(), rawCourse.getCourseRoom());
        }
    }
}
