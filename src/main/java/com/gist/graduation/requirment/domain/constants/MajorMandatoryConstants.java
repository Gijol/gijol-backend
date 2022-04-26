package com.gist.graduation.requirment.domain.constants;


import com.gist.graduation.user.taken_course.TakenCourse;

public class MajorMandatoryConstants {

    public static class EECS {
        public static final String EC = "EC";
        public static final TakenCourse EC3101 = new TakenCourse("전자공학실험", "EC3101", "3");
        public static final TakenCourse EC3102 = new TakenCourse("컴퓨터 시스템 이론 및 실험", "EC3102", "4");
    }


    public static class MaterialScience {
        public static final String MA = "MA";
        public static final TakenCourse MA2101 = new TakenCourse("재료과학", "MA2101", "3");
        public static final TakenCourse MA2102 = new TakenCourse("열역학", "MA2102", "3");
        public static final TakenCourse MA2103 = new TakenCourse("유기재료화학", "MA2103", "3");
        public static final TakenCourse MA2104 = new TakenCourse("고분자과학", "MA2104", "3");
        public static final TakenCourse MA3101 = new TakenCourse("재료과학", "MA3101", "3");
        public static final TakenCourse MA3102 = new TakenCourse("고분자과학", "MA3102", "3");
        public static final TakenCourse MA3104 = new TakenCourse("전자재료실험", "MA3104", "3");
        public static final TakenCourse MA3105 = new TakenCourse("유기재료실험", "MA3105", "3");
    }

    public static class Chemistry {
        public static final String CH = "CH";
        public static final TakenCourse CH2101 = new TakenCourse("분석화학", "CH2101", "3");
        public static final TakenCourse CH2102 = new TakenCourse("물리화학A", "CH2102", "3");
        public static final TakenCourse CH2103 = new TakenCourse("유기화학Ⅰ", "CH2103", "3");
        public static final TakenCourse CH2104 = new TakenCourse("물리화학 B", "CH2104", "3");
        public static final TakenCourse CH2105 = new TakenCourse("화학합성실험", "CH2105", "3");
        public static final TakenCourse CH3102 = new TakenCourse("화학합성실험", "CH3102", "3");
        public static final TakenCourse CH3103 = new TakenCourse("고급화학실험", "CH3103", "3");
        public static final TakenCourse CH3104 = new TakenCourse("물리화학 II", "CH3104", "3");
        public static final TakenCourse CH3105 = new TakenCourse("유기화학 II", "CH3105", "3");
        public static final TakenCourse CH3106 = new TakenCourse("생화학 Ⅰ", "CH3106", "3");
        public static final TakenCourse CH3107 = new TakenCourse("무기화학", "CH3107", "3");
    }

    public static class Physics {
        public static final String PS = "PS";
        public static final TakenCourse PS2101 = new TakenCourse("고전역학 및 연습Ⅰ", "PS2101", "3");
        public static final TakenCourse PS2102 = new TakenCourse("전자기학 및 연습 Ⅰ", "PS2102", "3");
        public static final TakenCourse PS2103 = new TakenCourse("전자기학 및 연습Ⅱ", "PS2103", "3");
        public static final TakenCourse PS3101 = new TakenCourse("전자기학 및 연습Ⅱ", "PS3101", "3");
        public static final TakenCourse PS3103 = new TakenCourse("양자물리 및 연습 I", "PS3103", "3");
        public static final TakenCourse PS3104 = new TakenCourse("양자물리 및 연습 II", "PS3104", "3");
        public static final TakenCourse PS3105 = new TakenCourse("열역학 및 통계물리", "PS3105", "3");
        public static final TakenCourse PS3106 = new TakenCourse("물리실험 I", "PS3106", "3");
        public static final TakenCourse PS3107 = new TakenCourse("수리물리 I", "PS3107", "3");
    }

    public static class Environment {
        public static final String EV = "EV";
        public static final TakenCourse EV3101 = new TakenCourse("환경공학", "EV3101", "3");
        public static final TakenCourse EV3104 = new TakenCourse("대기학", "EV3104", "3");
        public static final TakenCourse EV3105 = new TakenCourse("해양학", "EV3105", "3");
        public static final TakenCourse EV3106 = new TakenCourse("환경분석실험 I", "EV3106", "3");
        public static final TakenCourse EV3111 = new TakenCourse("지구시스템과학", "EV3111", "3");
        public static final TakenCourse EV4105 = new TakenCourse("지구환경 열역학", "EV4105", "3");
        public static final TakenCourse EV4106 = new TakenCourse("지구환경이동현상", "EV4106", "3");
        public static final TakenCourse EV4107 = new TakenCourse("환경분석실험 II", "EV4107", "3");
    }

    public static class Biology {
        public static final String BS = "BS";
        public static final TakenCourse BS2101 = new TakenCourse("유기화학 Ⅰ", "BS2101", "3");
        public static final TakenCourse BS2102 = new TakenCourse("분자생물학", "BS2102", "3");
        public static final TakenCourse BS2103 = new TakenCourse("생화학·분자생물학 실험", "BS2103", "3");
        public static final TakenCourse BS2104 = new TakenCourse("생화학 I", "BS2104", "3");
        public static final TakenCourse BS3101 = new TakenCourse("생화학 II", "BS3101", "3");
        public static final TakenCourse BS3105 = new TakenCourse("세포생물학", "BS3105", "3");
        public static final TakenCourse BS3111 = new TakenCourse("생화학·분자생물학 실험", "BS3111", "3");
        public static final TakenCourse BS3112 = new TakenCourse("세포·발생생물학 실험", "BS3112", "3");
        public static final TakenCourse BS3113 = new TakenCourse("생화학 I", "BS3113", "3");
    }

    public static class MechanicalEngineering {
        public static final String MC = "MC";
        public static final TakenCourse MC2100 = new TakenCourse("열역학", "MC2100", "3");
        public static final TakenCourse MC2100_1 = new TakenCourse("열역학 I", "MC2100", "3");
        public static final TakenCourse MC2101 = new TakenCourse("고체역학", "MC2101", "3");
        public static final TakenCourse MC2101_1 = new TakenCourse("고체역학 I", "MC2101", "3");
        public static final TakenCourse MC2102 = new TakenCourse("유체역학", "MC2102", "3");
        public static final TakenCourse MC2102_1 = new TakenCourse("유체역학 I", "MC2102", "3");
        public static final TakenCourse MC2103 = new TakenCourse("동역학", "MC2103", "3");
        public static final TakenCourse MC3103 = new TakenCourse("기구동역학", "MC3103", "3");
        public static final TakenCourse MC3105 = new TakenCourse("유체역학", "MC3105", "3");
        public static final TakenCourse MC3106 = new TakenCourse("기계공학실험Ⅰ", "MC3106", "3");
        public static final TakenCourse MC3107 = new TakenCourse("기계공학실험Ⅱ", "MC3107", "3");
        public static final TakenCourse MC3212 = new TakenCourse("기계공학실험Ⅰ", "MC3212", "3");
        public static final TakenCourse MC4101 = new TakenCourse("기계시스템설계 및 제작 I", "MC4101", "3");
    }

}
