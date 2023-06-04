package com.gist.graduation.requirment.domain.constants;


import com.gist.graduation.course.domain.CourseInfo;

public class MajorMandatoryConstants {

    public static class EECS {
        public static final String EC = "EC";
        public static final CourseInfo EC3101 = new CourseInfo("전자공학실험", "EC3101", "3");
        public static final CourseInfo EC3102 = new CourseInfo("컴퓨터 시스템 이론 및 실험", "EC3102", "4");
    }


    public static class MaterialScience {
        public static final String MA = "MA";
        public static final CourseInfo MA2101 = new CourseInfo("재료과학", "MA2101", "3");
        public static final CourseInfo MA2102 = new CourseInfo("열역학", "MA2102", "3");
        public static final CourseInfo MA2103 = new CourseInfo("유기재료화학", "MA2103", "3");
        public static final CourseInfo MA2104 = new CourseInfo("고분자과학", "MA2104", "3");
        public static final CourseInfo MA3101 = new CourseInfo("재료과학", "MA3101", "3");
        public static final CourseInfo MA3102 = new CourseInfo("고분자과학", "MA3102", "3");
        public static final CourseInfo MA3104 = new CourseInfo("전자재료실험", "MA3104", "3");
        public static final CourseInfo MA3105 = new CourseInfo("유기재료실험", "MA3105", "3");
    }

    public static class Chemistry {
        public static final String CH = "CH";
        public static final CourseInfo CH2101 = new CourseInfo("분석화학", "CH2101", "3");
        public static final CourseInfo CH2102 = new CourseInfo("물리화학A", "CH2102", "3");
        public static final CourseInfo CH2103 = new CourseInfo("유기화학Ⅰ", "CH2103", "3");
        public static final CourseInfo CH2104 = new CourseInfo("물리화학 B", "CH2104", "3");
        public static final CourseInfo CH2105 = new CourseInfo("화학합성실험", "CH2105", "3");
        public static final CourseInfo GS2202 = new CourseInfo("물리화학 Ⅰ", "GS2202", "3");
        //        public static final CourseInfo CH3102 = new CourseInfo("화학합성실험", "CH3102", "3"); 2018년 1학기까지 존재
//        public static final CourseInfo CH3103 = new CourseInfo("고급화학실험", "CH3103", "3"); 18학사편람에 필수 과목임
        public static final CourseInfo CH3104 = new CourseInfo("물리화학 II", "CH3104", "3");
        //        public static final CourseInfo CH3105 = new CourseInfo("유기화학 II", "CH3105", "3"); // 18학사편람에 필수 과목임
        public static final CourseInfo CH3106 = new CourseInfo("생화학 Ⅰ", "CH3106", "3");
        public static final CourseInfo CH3107 = new CourseInfo("무기화학", "CH3107", "3");
    }

    public static class Physics {
        public static final String PS = "PS";
        public static final CourseInfo PS2101 = new CourseInfo("고전역학 및 연습Ⅰ", "PS2101", "3");
        public static final CourseInfo PS2102 = new CourseInfo("전자기학 및 연습 Ⅰ", "PS2102", "3");
        public static final CourseInfo PS2103 = new CourseInfo("전자기학 및 연습Ⅱ", "PS2103", "3");
        public static final CourseInfo PS3101 = new CourseInfo("전자기학 및 연습Ⅱ", "PS3101", "3");
        public static final CourseInfo PS3103 = new CourseInfo("양자물리 및 연습 I", "PS3103", "3");
        public static final CourseInfo PS3104 = new CourseInfo("양자물리 및 연습 II", "PS3104", "3");
        public static final CourseInfo PS3105 = new CourseInfo("열역학 및 통계물리", "PS3105", "3");
        public static final CourseInfo PS3106 = new CourseInfo("물리실험 I", "PS3106", "3");
        public static final CourseInfo PS3107 = new CourseInfo("수리물리 I", "PS3107", "3");
    }

    public static class Environment {
        public static final String EV = "EV";
        public static final CourseInfo EV3101 = new CourseInfo("환경공학", "EV3101", "3");
        public static final CourseInfo EV3104 = new CourseInfo("대기학", "EV3104", "3");
        public static final CourseInfo EV3105 = new CourseInfo("해양학", "EV3105", "3");
        public static final CourseInfo EV3106 = new CourseInfo("환경분석실험 I", "EV3106", "3");
        public static final CourseInfo EV3111 = new CourseInfo("지구시스템과학", "EV3111", "3");
        public static final CourseInfo EV4105 = new CourseInfo("지구환경 열역학", "EV4105", "3");
        public static final CourseInfo EV4106 = new CourseInfo("지구환경이동현상", "EV4106", "3");
        public static final CourseInfo EV4107 = new CourseInfo("환경분석실험 II", "EV4107", "3");
    }

    public static class Biology {
        public static final String BS = "BS";
        public static final CourseInfo BS2101 = new CourseInfo("유기화학 Ⅰ", "BS2101", "3");
        public static final CourseInfo BS2102 = new CourseInfo("분자생물학", "BS2102", "3");
        public static final CourseInfo BS2104 = new CourseInfo("생화학 I", "BS3113", "3");  // 2019까지 과목코드
        public static final CourseInfo BS2104_1 = new CourseInfo("생화학 I", "BS2104", "3");
        public static final CourseInfo BS3101 = new CourseInfo("생화학 II", "BS3101", "3");
        public static final CourseInfo BS3105 = new CourseInfo("세포생물학", "BS3105", "3");
        public static final CourseInfo BS2103 = new CourseInfo("생화학·분자생물학 실험", "BS3111", "3"); // 2019까지 과목코드
        public static final CourseInfo BS2103_1 = new CourseInfo("생화학·분자생물학 실험", "BS2103", "3");
        public static final CourseInfo BS3112 = new CourseInfo("세포·발생생물학 실험", "BS3112", "3");
    }

    public static class MechanicalEngineering {
        public static final String MC = "MC";
        public static final CourseInfo MC2100 = new CourseInfo("열역학", "MC2100", "3");
        public static final CourseInfo MC2100_1 = new CourseInfo("열역학 I", "MC2100", "3");
        public static final CourseInfo MC2101 = new CourseInfo("고체역학", "MC2101", "3");
        public static final CourseInfo MC2101_1 = new CourseInfo("고체역학 I", "MC2101", "3");
        public static final CourseInfo MC2102 = new CourseInfo("유체역학", "MC2102", "3");
        public static final CourseInfo MC2102_1 = new CourseInfo("유체역학 I", "MC2102", "3");
        public static final CourseInfo MC2103 = new CourseInfo("동역학", "MC2103", "3");
        public static final CourseInfo MC3103 = new CourseInfo("기구동역학", "MC3103", "3");
        public static final CourseInfo MC3105 = new CourseInfo("유체역학", "MC3105", "3");
        public static final CourseInfo MC3106 = new CourseInfo("기계공학실험Ⅰ", "MC3106", "3");
        public static final CourseInfo MC3107 = new CourseInfo("기계공학실험Ⅱ", "MC3107", "3");
        public static final CourseInfo MC3212 = new CourseInfo("기계공학실험Ⅰ", "MC3212", "3");
        public static final CourseInfo MC4101 = new CourseInfo("기계시스템설계 및 제작 I", "MC4101", "3");
    }

}
