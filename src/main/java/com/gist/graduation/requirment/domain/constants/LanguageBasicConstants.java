package com.gist.graduation.requirment.domain.constants;

import com.gist.graduation.course.domain.CourseInfo;

import java.util.List;

public class LanguageBasicConstants {

    public static final List<CourseInfo> WRITING_COURSE_INFOS = List.of(
            new CourseInfo("글쓰기의 기초: 학술적 글쓰기", "GS1512", "3"),
            new CourseInfo("글쓰기의 기초: 논리적 글쓰기", "GS1511", "3"),
            new CourseInfo("글쓰기의 기초: 창의적 글쓰기", "GS1513", "3"),
            new CourseInfo("심화 글쓰기: 과학 글쓰기", "GS1531", "3"),
            new CourseInfo("심화 글쓰기: 고전 읽기와 글쓰기", "GS1532", "3"),
            new CourseInfo("심화 글쓰기: 비평적 글쓰기", "GS1533", "3"),
            new CourseInfo("심화 글쓰기: 디지털 스토리텔링", "GS1534", "3")
    );

    public static final CourseInfo ENGLISH_I_PRESENTATION = new CourseInfo("영어 Ⅰ: 발표와 토론", "GS1603", "2");
    public static final CourseInfo ENGLISH_I = new CourseInfo("영어 Ⅰ: 신입생 영어", "GS1601", "2");
    public static final CourseInfo ENGLISH_II = new CourseInfo("영어 II : 이공계 글쓰기 입문", "GS2652", "2");
}
