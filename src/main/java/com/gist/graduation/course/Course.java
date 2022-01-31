package com.gist.graduation.course;

import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@Getter
@ToString
public class Course {
    private final String code;
    private final String name;
    private final int credit;

    public Course(String code, String name, int credit) {
        this.code = code;
        this.name = name;
        this.credit = credit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return credit == course.credit && Objects.equals(code, course.code) && Objects.equals(name, course.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name, credit);
    }
}
