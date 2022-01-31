package com.gist.graduation.user.taken_course;

public enum CourseType {
    E,
    M;

    public static CourseType stringOf(String type) {
        for (CourseType courseType : CourseType.values()) {
            if (courseType.name().equals(type)) {
                return courseType;
            }
        }
        return null;
    }

}
