package com.gist.graduation.course.application.description;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DescriptionJsonDto {
    @JsonProperty("courseCode")
    private String courseCode;
    @JsonProperty("courseDescription")
    private String courseDescription;

    public DescriptionJsonDto(String courseCode, String courseDescription) {
        this.courseCode = courseCode;
        this.courseDescription = courseDescription;
    }
}
