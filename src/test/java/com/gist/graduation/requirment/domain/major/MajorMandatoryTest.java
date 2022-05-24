package com.gist.graduation.requirment.domain.major;

import com.gist.graduation.user.taken_course.TakenCourse;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.gist.graduation.requirment.domain.constants.MajorMandatoryConstants.Biology.*;
import static com.gist.graduation.requirment.domain.constants.MajorMandatoryConstants.Chemistry.*;
import static com.gist.graduation.requirment.domain.constants.MajorMandatoryConstants.MechanicalEngineering.*;

class MajorMandatoryTest {

    private UserTakenCoursesList userTakenCoursesList;

    @BeforeEach
    void setup() {
        userTakenCoursesList = new UserTakenCoursesList();
    }

    private static Stream<List<TakenCourse>> mechanicalMajorArguments() {
        List<List<TakenCourse>> arguments = new ArrayList<>();
        arguments.add(List.of(MC2100, MC2101, MC2103, MC3106, MC3107));
        arguments.add(List.of(MC2100, MC3106, MC3107));
        arguments.add(List.of(MC2100, MC2101, MC2102_1, MC2103, MC3106, MC3107));
        arguments.add(List.of(MC2100_1, MC2101_1, MC2102_1, MC2103, MC3106, MC3107));
        arguments.add(List.of());
        return arguments.stream();
    }


    @ParameterizedTest
    @MethodSource("mechanicalMajorArguments")
    void checkMechanicalMandatory(List<TakenCourse> majorCourseLists) {
        Major major = new Major();
        userTakenCoursesList.addAll(majorCourseLists);
        major.checkRequirementByStudentId(20, userTakenCoursesList, MajorType.MC);
        System.out.println(major);
    }

    private static Stream<List<TakenCourse>> biologyMajorArguments() {
        List<List<TakenCourse>> arguments = new ArrayList<>();
        arguments.add(List.of(BS2101, BS2102, BS2103, BS2104));
        arguments.add(List.of(BS2101, BS2102, BS2103, BS2104, BS3101, BS3105, BS3112));
        arguments.add(List.of(BS2101, BS2102, BS2103_1, BS2104, BS3101, BS3112));
        arguments.add(List.of());
        return arguments.stream();
    }


    @ParameterizedTest
    @MethodSource("biologyMajorArguments")
    void checkBiologyMandatory(List<TakenCourse> majorCourseLists) {
        Major major = new Major();
        userTakenCoursesList.addAll(majorCourseLists);
        major.checkRequirementByStudentId(20, userTakenCoursesList, MajorType.BS);
        System.out.println(major);
    }

    private static Stream<List<TakenCourse>> chemistryMajorArguments() {
        List<List<TakenCourse>> arguments = new ArrayList<>();
        arguments.add(List.of(CH2101, CH2102, CH2103, CH2104, CH2105, CH3106, CH3107));
        arguments.add(List.of(CH2101, CH2102, CH2103, CH2104, CH2105));
        arguments.add(List.of(CH2101, CH2102, CH2103, CH2105, CH3106, GS2202));
        arguments.add(List.of(CH2101, CH2102, CH2103, CH2105, CH3106, CH3104));
        arguments.add(List.of());
        return arguments.stream();
    }


    @ParameterizedTest
    @MethodSource("chemistryMajorArguments")
    void checkChemistryMandatory(List<TakenCourse> majorCourseLists) {
        Major major = new Major();
        userTakenCoursesList.addAll(majorCourseLists);
        major.checkRequirementByStudentId(20, userTakenCoursesList, MajorType.CH);
        System.out.println(major);
    }

}