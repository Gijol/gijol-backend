package com.gist.graduation.utils;

public enum FailGrade {
    F,
    U;

    public static boolean isFail(String grade) {
        for (FailGrade failGrade : FailGrade.values()) {
            if (failGrade.name().equals(grade)) {
                return true;
            }
        }
        return false;
    }
}