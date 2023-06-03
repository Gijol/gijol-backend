package com.gist.graduation.fixture;

import com.gist.graduation.requirment.domain.major.MajorType;
import com.gist.graduation.user.domain.User;
import com.gist.graduation.user.domain.UserTakenCourse;
import com.gist.graduation.user.taken_course.CourseType;

import java.util.List;

public class Fixtures {

    public static User createUser() {
        return User.builder()
                .name("최은기")
                .email("choieungi@gm.gist.ac.kr")
                .majorType(MajorType.EC)
                .studentId("20205188")
                .userTakenCourses(createUserTakenCourse())
                .build();
    }

    public static List<UserTakenCourse> createUserTakenCourse() {
        return List.of(new UserTakenCourse(2020, "1학기", CourseType.필수, "컴퓨터 프로그래밍", "GS1234", 3, "A+"),
                new UserTakenCourse(2020, "1학기", CourseType.필수, "일반 화학 실험", "GS1235", 1, "B0"),
                new UserTakenCourse(2020, "2학기", CourseType.필수, "일반 물리 실험", "GS1235", 1, "A0"),
                new UserTakenCourse(2021, "1학기", CourseType.필수, "소프트웨어 활용과 코딩", "GS1235", 2, "A+"),
                new UserTakenCourse(2022, "1학기", CourseType.PPE, "인간의 마음과 행동들", "GS2239", 3, "C0")
        );
    }
}
