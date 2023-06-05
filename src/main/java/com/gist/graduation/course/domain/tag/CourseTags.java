package com.gist.graduation.course.domain.tag;


import lombok.Getter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Embeddable
public class CourseTags {

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    @BatchSize(size = 200)
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
