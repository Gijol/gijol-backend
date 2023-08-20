package com.gist.graduation.course.domain.tag;

import java.util.Arrays;

public enum CourseTagType {
    HUS,
    PPE,
    전공,
    부전공;

    public static CourseTagType containsStringIgnoreCase(String string) {
        return Arrays.stream(values())
                .filter(s -> s.name().equalsIgnoreCase(string))
                .findAny()
                .orElse(null);
    }

}
