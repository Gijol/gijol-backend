package com.gist.graduation.utils;

import com.gist.graduation.course.domain.CourseInfo;
import lombok.Getter;

import java.util.Objects;

@Getter
public class RegisteredCourse {
    private final String index;
    private final int year;
    private final String semester;
    private final String type;
    private final String name;
    private final String code;
    private final int credit;
    private String liberalArtType = null;
    private final String professor;
    private final String time;
    private final String room;
    private final String prerequisite;

    public RegisteredCourse(String index, int year, String semester, String type, String name, String code, int credit, String professor, String time, String room, String prerequisite) {
        this.index = index;
        this.year = year;
        this.semester = semester;
        this.type = type;
        this.name = name;
        this.code = code;
        this.credit = credit;
        this.professor = professor;
        this.time = time;
        this.room = room;
        this.prerequisite = prerequisite;
    }


    public void setLiberalArtType(String liberalArtType) {
        this.liberalArtType = liberalArtType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegisteredCourse)) return false;
        RegisteredCourse that = (RegisteredCourse) o;
        return credit == that.credit && Objects.equals(name, that.name) && Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, code, credit);
    }


    public CourseInfo toCourseInfo() {
        return new CourseInfo(name, code, credit);
    }
}
