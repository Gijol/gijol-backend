package com.gist.graduation.course.domain;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class CourseTags {

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CourseTag> courseTags = new ArrayList<>();

    public CourseTags(List<CourseTag> courseTags) {
        this.courseTags = courseTags;
    }

    public void addCourseTag(CourseTag courseTag) {
        courseTags.add(courseTag);
    }

}
