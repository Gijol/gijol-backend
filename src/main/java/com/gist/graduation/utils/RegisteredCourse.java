package com.gist.graduation.utils;

import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@Getter
@ToString
public class RegisteredCourse {
    private final String index;
    private final int year;
    private final String semester;
    private final String type;
    private final String name;
    private final String code;
    private final int credit;
    private String liberalArtType = null;

    public RegisteredCourse(String index, int year, String semester, String type, String name, String code, int credit) {
        this.index = index;
        this.year = year;
        this.semester = semester;
        this.type = type;
        this.name = name;
        this.code = code;
        this.credit = credit;
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


}
