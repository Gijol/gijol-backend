package com.gist.graduation.requirment.domain.constants;

import com.gist.graduation.course.domain.CourseInfo;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HumanitiesExceptionConstants {

    @RequiredArgsConstructor
    public enum NotHumanities {
        GS2651(new CourseInfo("영어 토론과 논증", "GS2651", "2")),
        GS2652(new CourseInfo("영어 II : 이공계 글쓰기 입문", "GS2652", "2")),
        GS2653(new CourseInfo("바른 논문쓰기(이해와 토론)", "GS2653", "2")),
        GS2654(new CourseInfo("창의적 영어 표현법", "GS2654", "2")),
        GS2655(new CourseInfo("디지털 시대의 저널리즘과 과학 기사 쓰기", "GS2655", "2")),
        GS3651(new CourseInfo("영어 III. 이공계 논문쓰기", "GS3651", "2")),
        GS2806(new CourseInfo("우주와 생명", "GS2806", "3")),
        GS2809(new CourseInfo("아름다운 지구", "GS2809", "3")),
        GS2811(new CourseInfo("인간과 바다", "GS2811", "3")),
        GS2835(new CourseInfo("인간과 식물", "GS2835", "2")),
        GS2836(new CourseInfo("(MOOC 지정) 기후변화와 미래기술", "GS2836", "3")),
        GS3804(new CourseInfo("현대고급기기분석", "GS3804", "3")),
        GS4471(new CourseInfo("인류세의 우리", "GS4471", "1"));

        private final CourseInfo course;

        public static void removeHumanitiesException(List<CourseInfo> registeredCourses) {
            registeredCourses.removeAll(getHumanitiesExceptions());
        }

        public static List<CourseInfo> getHumanitiesExceptions() {
            return Arrays.stream(values())
                    .map(s -> s.course)
                    .collect(Collectors.toList());
        }
    }

    @RequiredArgsConstructor
    public enum GSC {
        GS2541(new CourseInfo("서양음악의 이해", "GS2541", "3")),
        GS2542(new CourseInfo("오페라와 판소리", "GS2542", "3")),
        GS2543(new CourseInfo("현대 예술의 이해", "GS2543", "3")),
        GS2791(new CourseInfo("유라시아 깊이 읽기", "GS2791", "3")),
        GS2792(new CourseInfo("커뮤니케이션론 I", "GS2792", "3")),
        GS2810(new CourseInfo("빅히스토리: 우주와 인간의 역사", "GS2810", "3")),
        GS2810_1(new CourseInfo("우주와 인간의 역사", "GS2810", "2")),
        GS2815(new CourseInfo("문화공학 I", "GS2815", "3")),
        GS2816(new CourseInfo("문화공학 II", "GS2816", "3")),
        GS2817(new CourseInfo("문화와 도시재생 연구", "GS2817", "3")),
        GS2818(new CourseInfo("융합사고론", "GS2818", "3")),
        GS2819(new CourseInfo("이미지론: 보는 법", "GS2819", "3")),
        GS2821(new CourseInfo("대중강연:융합시대의 리더 육성 프로젝트", "GS2821", "2")),
        GS2822(new CourseInfo("AI와 나", "GS2822", "2")),
        GS2823(new CourseInfo("수학의 위대한 순간들 - AI", "GS2823", "3")),
        GS2931(new CourseInfo("독일어 1", "GS2931", "2")),
        GS2932(new CourseInfo("독일어 2", "GS2932", "2")),
        GS3506(new CourseInfo("서양연극사", "GS3506", "3")),
        GS3507(new CourseInfo("콘텐츠 기획과 제작", "GS3507", "3"));

        private final CourseInfo course;

        public static void removeGSCCourses(List<CourseInfo> registeredCourses) {
            registeredCourses.removeAll(getGSCCoursesList());
        }

        public static List<CourseInfo> getGSCCoursesList() {
            return Arrays.stream(values())
                    .map(s -> s.course)
                    .collect(Collectors.toList());
        }
    }

    @RequiredArgsConstructor
    public enum HUSAmbiguousHumanities {
        GS2620(new CourseInfo("철학의 근본 문제들", "GS2620", "3")),
        GS2661(new CourseInfo("논리와 비판적 사고", "GS2661", "3")),
        GS2661_1(new CourseInfo("논리학 입문", "GS2661", "3")),
        GS3631(new CourseInfo("마음과 컴퓨터", "GS3631", "3")),
        GS3632(new CourseInfo("의사결정 이론과 합리적 선택", "GS3632", "3")),
        GS3632_1(new CourseInfo("합리적 판단과 선택", "GS3632", "3")),
        GS3633(new CourseInfo("과학철학 특강", "GS3633", "3")),
        GS3633_1(new CourseInfo("서양 현대 철학 강독", "GS3633", "3")),
        GS3663(new CourseInfo("종교와 과학", "GS3663", "3")),
        GS3831(new CourseInfo("과학기술학의 이해: 과학사회논쟁의 쟁점과 윤리", "GS3831", "3")),
        GS3839(new CourseInfo("질병과 사회", "GS3839", "3")),
        GS3861(new CourseInfo("신경과학과 법", "GS3861", "3"));

        private final CourseInfo course;

        public static void removeNotHUS(List<CourseInfo> registeredCourses) {
            registeredCourses.removeAll(
                    Arrays.stream(values())
                            .map(s -> s.course)
                            .collect(Collectors.toList())
            );
        }

        public static void addPPE(List<CourseInfo> registeredCourses) {
            registeredCourses.addAll(
                    Arrays.stream(values())
                            .map(s -> s.course)
                            .collect(Collectors.toList())
            );
        }


    }

    @RequiredArgsConstructor
    public enum PPEAmbiguousHumanities {
        GS2814(new CourseInfo("유토피아 픽션과 테크놀로지", "GS2814", "3"));

        private final CourseInfo course;

        public static void removeNotPPE(List<CourseInfo> registeredCourses) {
            registeredCourses.removeAll(
                    Arrays.stream(values())
                            .map(s -> s.course)
                            .collect(Collectors.toList())
            );
        }

        public static void addHus(List<CourseInfo> registeredCourses) {
            registeredCourses.addAll(
                    Arrays.stream(values())
                            .map(s -> s.course)
                            .collect(Collectors.toList())
            );
        }

    }
}
