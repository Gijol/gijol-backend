package com.gist.graduation.requirment.domain.science;

import com.gist.graduation.user.taken_course.TakenCourse;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.gist.graduation.requirment.domain.constants.ScienceBasicConstants.Science.*;

@AllArgsConstructor
@ToString
public enum ScienceEnum {
    PHYSICS_BLOCK,
    CHEMISTRY_BLOCK,
    BIOLOGY_BLOCK;

    private final List<TakenCourse> courseList = new ArrayList<>();

    static {
        PHYSICS_BLOCK.courseList.addAll(PHYSICS);
        PHYSICS_BLOCK.courseList.add(PHYSICS_EXPERIMENT);
        CHEMISTRY_BLOCK.courseList.addAll(CHEMISTRY);
        CHEMISTRY_BLOCK.courseList.add(CHEMISTRY_EXPERIMENT);
        BIOLOGY_BLOCK.courseList.addAll(BIOLOGY);
        BIOLOGY_BLOCK.courseList.add(BIOLOGY_EXPERIMENT);
    }

    public static void checkScienceBasicCourses(ScienceBasic scienceBasic, UserTakenCoursesList userTakenCoursesList) {
        for (ScienceEnum scienceEnum : values()) {
            List<TakenCourse> takenCourses = userTakenCoursesList.getTakenCourses().stream()
                    .filter(scienceEnum.courseList::contains)
                    .collect(Collectors.toList());
            scienceBasic.getUserTakenCoursesList().addAll(takenCourses);
        }
        addSoftwareBasic(scienceBasic, userTakenCoursesList);
    }

    private static void addSoftwareBasic(ScienceBasic scienceBasic, UserTakenCoursesList userTakenCoursesList) {
        if (userTakenCoursesList.contains(SOFTWARE_BASIC_AND_CODING)) {
            scienceBasic.addCourse(SOFTWARE_BASIC_AND_CODING);
        }
    }

    public static ScienceVerifier ofScienceVerifier(UserTakenCoursesList userTakenCoursesList) {
        ScienceBlock physicsBlock = new ScienceBlock();
        ScienceBlock chemistryBlock = new ScienceBlock();
        ScienceBlock biologyBlock = new ScienceBlock();
        int i = 0;

        for (ScienceEnum scienceEnum : values()) {
            List<TakenCourse> takenCourses = userTakenCoursesList.getTakenCourses().stream()
                    .filter(scienceEnum.courseList::contains)
                    .collect(Collectors.toList());

            if (i == 0) {
                physicsBlock = new ScienceBlock(takenCourses, ofStatus(takenCourses), "물리");
            }

            if (i == 1) {
                chemistryBlock = new ScienceBlock(takenCourses, ofStatus(takenCourses), "화학");
            }

            if (i == 2) {
                biologyBlock = new ScienceBlock(takenCourses, ofStatus(takenCourses), "생명");
            }
            i++;
        }
        return ScienceVerifier.of(physicsBlock, chemistryBlock, biologyBlock);
    }


    public enum Status {
        FULL,
        HALF,
        EMPTY
    }

    public static Status ofStatus(List<TakenCourse> takenCourses) {
        if (takenCourses.size() >= 2) {
            return Status.FULL;
        }
        if (takenCourses.size() == 1) {
            return Status.HALF;
        }
        return Status.EMPTY;
    }
}
