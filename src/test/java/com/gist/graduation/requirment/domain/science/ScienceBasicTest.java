package com.gist.graduation.requirment.domain.science;

import com.gist.graduation.requirment.domain.constants.MajorMandatoryConstants;
import com.gist.graduation.requirment.domain.constants.ScienceBasicConstants;
import com.gist.graduation.requirment.domain.major.Major;
import com.gist.graduation.requirment.domain.major.MajorType;
import com.gist.graduation.user.taken_course.TakenCourse;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.gist.graduation.requirment.domain.constants.MajorMandatoryConstants.MechanicalEngineering.*;
import static com.gist.graduation.requirment.domain.constants.MajorMandatoryConstants.MechanicalEngineering.MC3107;
import static com.gist.graduation.requirment.domain.constants.ScienceBasicConstants.Math.CALCULUS;
import static com.gist.graduation.requirment.domain.constants.ScienceBasicConstants.Math.CORE_MATH;
import static com.gist.graduation.requirment.domain.constants.ScienceBasicConstants.Science.*;

public class ScienceBasicTest {
    private UserTakenCoursesList userTakenCoursesList;

    private static final TakenCourse GS1101 = new TakenCourse("일반물리학 및 연습 I", "GS1101", "3");
    private static final TakenCourse GS1103 = new TakenCourse("고급일반물리학 및 연습 I", "GS1103", "3");
    private static final TakenCourse GS1201 = new TakenCourse("일반화학 및 연습 I", "GS1201", "3");
    private static final TakenCourse GS1301 = new TakenCourse("생물학", "GS1301", "3");
    private static final TakenCourse GS1401 = new TakenCourse("컴퓨터 프로그래밍", "GS1401", "3");

    @BeforeEach
    void setup() {
        userTakenCoursesList = new UserTakenCoursesList();
    }

    private static Stream<List<TakenCourse>> scienceBasicArguments() {
        List<List<TakenCourse>> arguments = new ArrayList<>();
        arguments.add(List.of());
        arguments.add(List.of(CALCULUS.get(0), CORE_MATH.get(3),
                GS1101, GS1201, PHYSICS_EXPERIMENT));
        arguments.add(List.of(CALCULUS.get(0), CORE_MATH.get(3),GS1103, GS1201, PHYSICS_EXPERIMENT, CHEMISTRY_EXPERIMENT, COMPUTER_PROGRAMMING));
        arguments.add(List.of(CALCULUS.get(0), CORE_MATH.get(3),GS1103, GS1201, COMPUTER_PROGRAMMING));
        arguments.add(List.of(CALCULUS.get(0), CORE_MATH.get(3),GS1103, GS1201, BIOLOGY_EXPERIMENT, COMPUTER_PROGRAMMING));
        arguments.add(List.of(CALCULUS.get(0), CORE_MATH.get(3),GS1103, GS1201, PHYSICS_EXPERIMENT, GS1301, GS1401));
        arguments.add(List.of(CALCULUS.get(0), CORE_MATH.get(3), GS1401, GS1101, GS1201));
        arguments.add(List.of(CALCULUS.get(0), CORE_MATH.get(3), GS1101, GS1201));
        arguments.add(List.of(CALCULUS.get(0), CORE_MATH.get(3), GS1101, GS1401, PHYSICS_EXPERIMENT));
        arguments.add(List.of(CALCULUS.get(0), CORE_MATH.get(3), GS1101, GS1401, PHYSICS_EXPERIMENT, BIOLOGY_EXPERIMENT));
        arguments.add(List.of(CALCULUS.get(0), CORE_MATH.get(3), GS1101, GS1401, GS1301, PHYSICS_EXPERIMENT, CHEMISTRY_EXPERIMENT));
        arguments.add(List.of(CALCULUS.get(0), CORE_MATH.get(3), GS1101, GS1301, PHYSICS_EXPERIMENT, CHEMISTRY_EXPERIMENT, SOFTWARE_BASIC_AND_CODING));
        return arguments.stream();
    }

    @ParameterizedTest
    @MethodSource("scienceBasicArguments")
    void checkMechanicalMandatory(List<TakenCourse> sciencebasic) {
        ScienceBasic scienceBasic = new ScienceBasic();
        userTakenCoursesList.addAll(sciencebasic);
        scienceBasic.checkRequirementByStudentId(20, userTakenCoursesList, MajorType.MC);
        System.out.println(scienceBasic);
    }

    @Test
    void test(){
        ScienceBasic scienceBasic = new ScienceBasic();
        userTakenCoursesList.addAll(List.of(CALCULUS.get(0), CORE_MATH.get(3), GS1101, GS1201));
        scienceBasic.checkRequirementByStudentId(20, userTakenCoursesList, MajorType.MC);
        System.out.println(scienceBasic);

    }

}
