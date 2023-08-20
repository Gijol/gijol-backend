package com.gist.graduation.course.domain.tag;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gist.graduation.common.BaseEntity;
import com.gist.graduation.course.domain.course.Course;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CourseTag extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private CourseTagType courseTagType;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;

    public CourseTag(CourseTagType courseTagType, Course course) {
        this.courseTagType = courseTagType;
        this.course = course;
    }
}
