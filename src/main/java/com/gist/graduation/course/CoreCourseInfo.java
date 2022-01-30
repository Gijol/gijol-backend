package com.gist.graduation.course;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CoreCourseInfo {
    private String index;
    private String code;
    private String name;
    private int credit;

    public CoreCourseInfo(String index, String code, String name, int credit) {
        this.index = index;
        this.code = code;
        this.name = name;
        this.credit = credit;
    }
}
