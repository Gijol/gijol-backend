package com.gist.graduation.course.domain.course;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Semester {

    private int year;
    private String semester;

    public Semester(int year, String semester) {
        this.year = year;
        this.semester = semester;
    }

}
