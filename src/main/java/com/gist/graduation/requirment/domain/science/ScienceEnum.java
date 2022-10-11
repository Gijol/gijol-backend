package com.gist.graduation.requirment.domain.science;

import com.gist.graduation.course.domain.CourseInfo;
import com.gist.graduation.config.exception.ApplicationException;
import com.gist.graduation.requirment.domain.constants.ScienceBasicConstants;
import com.gist.graduation.user.taken_course.TakenCourse;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.gist.graduation.requirment.domain.constants.ScienceBasicConstants.Science.*;

@AllArgsConstructor
@ToString
public enum ScienceEnum {
    PHYSICS_BLOCK,
    CHEMISTRY_BLOCK,
    BIOLOGY_BLOCK;

    public static final String PHYSICS = "물리";
    public static final String CHEMISTRY = "화학";
    public static final String BIOLOGY = "생명";
    private final List<CourseInfo> courseList = new ArrayList<>();

    static {
        PHYSICS_BLOCK.courseList.addAll(ScienceBasicConstants.Science.PHYSICS);
        PHYSICS_BLOCK.courseList.add(PHYSICS_EXPERIMENT);
        CHEMISTRY_BLOCK.courseList.addAll(ScienceBasicConstants.Science.CHEMISTRY);
        CHEMISTRY_BLOCK.courseList.add(CHEMISTRY_EXPERIMENT);
        BIOLOGY_BLOCK.courseList.addAll(ScienceBasicConstants.Science.BIOLOGY);
        BIOLOGY_BLOCK.courseList.add(BIOLOGY_EXPERIMENT);
    }

    public static void checkScienceBasicCourses(ScienceBasic scienceBasic, UserTakenCoursesList inputUserTakenCourses) {
        UserTakenCoursesList userTakenCourses = scienceBasic.getUserTakenCoursesList();

        for (ScienceEnum scienceEnum : values()) {
            List<TakenCourse> takenCourses = inputUserTakenCourses.getTakenCourses().stream()
                    .filter(s -> s.belongsToCourseInfosAny(scienceEnum.courseList))
                    .collect(Collectors.toList());
            scienceBasic.getUserTakenCoursesList().addAll(takenCourses);
        }
        addSoftwareBasic(scienceBasic, inputUserTakenCourses);
    }

    // software basic and coding check and add
    private static void addSoftwareBasic(ScienceBasic scienceBasic, UserTakenCoursesList inputUserTakenCourses) {
        inputUserTakenCourses.getTakenCourses().forEach(s -> {
            if (s.equalsCourseInfo(SOFTWARE_BASIC_AND_CODING)) scienceBasic.getUserTakenCoursesList().add(s);
        });
    }

    public static ScienceVerifier ofScienceVerifier(UserTakenCoursesList inputUserTakenCourses) {

        List<ScienceBlock> scienceBlockList = Arrays.stream(values()).
                map(scienceEnum -> {
                    List<TakenCourse> scienceCourses = inputUserTakenCourses.getTakenCourses().stream()
                            .filter(s -> s.belongsToCourseInfosAny(scienceEnum.courseList))
                            .collect(Collectors.toList());

                    if (scienceEnum.name().equals(PHYSICS_BLOCK.name())) {
                        return new ScienceBlock(scienceCourses, ofStatus(scienceCourses), PHYSICS);
                    } else if (scienceEnum.name().equals(CHEMISTRY_BLOCK.name())) {
                        return new ScienceBlock(scienceCourses, ofStatus(scienceCourses), CHEMISTRY);
                    } else if (scienceEnum.name().equals(BIOLOGY_BLOCK.name())) {
                        return new ScienceBlock(scienceCourses, ofStatus(scienceCourses), BIOLOGY);
                    }
                    throw new ApplicationException("기초 과학 블록을 찾을 수 없습니다.");
                })
                .collect(Collectors.toList());

        return ScienceVerifier.of(scienceBlockList.get(0), scienceBlockList.get(1), scienceBlockList.get(2));
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
