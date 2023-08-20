package com.gist.graduation.course.domain.course.vo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class SemesterInfo {

    private int year;
    private String semester;

    public SemesterInfo(int year, String semester) {
        this.year = year;
        this.semester = semester;
    }

}
