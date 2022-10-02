package com.gist.graduation.course.domain.tag;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gist.graduation.course.domain.course.Course;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CourseTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private CourseTagType courseTagType;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;

    public CourseTag(CourseTagType courseTagType, Course course) {
        this(null, courseTagType, course);
    }

    private CourseTag(Long id, CourseTagType courseTagType, Course course) {
        this.id = id;
        this.courseTagType = courseTagType;
        this.course = course;
    }
}
