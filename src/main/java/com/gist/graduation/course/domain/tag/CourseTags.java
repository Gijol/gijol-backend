package com.gist.graduation.course.domain.tag;


import lombok.Getter;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Embeddable
public class CourseTags {

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CourseTag> courseTags = new ArrayList<>();

    public CourseTags(List<CourseTag> courseTags) {
        this.courseTags = courseTags;
    }

    public CourseTags() {
        this.courseTags = new ArrayList<>();
    }

    public void addCourseTag(CourseTag courseTag) {
        courseTags.add(courseTag);
    }

}
