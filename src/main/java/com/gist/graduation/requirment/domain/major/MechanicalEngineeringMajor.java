package com.gist.graduation.requirment.domain.major;

import com.gist.graduation.course.domain.CourseInfo;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static com.gist.graduation.requirment.domain.constants.MajorMandatoryConstants.MechanicalEngineering.*;

@RequiredArgsConstructor
public enum MechanicalEngineeringMajor implements MajorInterface {

    FROM2018(List.of(18, 19, 20, 21, 22, 23, 24), List.of(MC2100, MC2100_1, MC2101, MC2101_1, MC2102, MC2102_1, MC2103, MC3106, MC3107));

    private final List<Integer> studentId;
    private final List<CourseInfo> mandatoryCourses;


    @Override
    public void addLackOfMandatoryCourses(Major major, List<CourseInfo> mandatoryCourses) {
        UserTakenCoursesList userTakenCourses = major.getUserTakenCoursesList();

        List<CourseInfo> lackOfMandatoryCourses = mandatoryCourses.stream()
                .filter(userTakenCourses::notExist)
                .collect(Collectors.toList());

        removeDuplication(lackOfMandatoryCourses);

        for (CourseInfo lackOfMandatoryCourse : lackOfMandatoryCourses) {
            major.addMessage(String.format("%s를 수강해야 합니다.", lackOfMandatoryCourse.toString()));
        }
    }

    private void removeDuplication(List<CourseInfo> lackOfMandatoryClass) {
        List<CourseInfo> thermodynamics = List.of(MC2100, MC2100_1);
        List<CourseInfo> solidMechanics = List.of(MC2101, MC2101_1);
        List<CourseInfo> fluidMechanics = List.of(MC2102, MC2102_1);

        removeDuplicate(lackOfMandatoryClass, thermodynamics, MC2100);
        removeDuplicate(lackOfMandatoryClass, solidMechanics, MC2101);
        removeDuplicate(lackOfMandatoryClass, fluidMechanics, MC2102);
    }

    private void removeDuplicate(List<CourseInfo> userTakenMandatoryCourses, List<CourseInfo> duplicatedNameCourse, CourseInfo oldCourse) {
        if (userTakenMandatoryCourses.stream().anyMatch(duplicatedNameCourse::contains)) {
            if (userTakenMandatoryCourses.containsAll(duplicatedNameCourse)) {
                userTakenMandatoryCourses.remove(oldCourse);
                return;
            }
            userTakenMandatoryCourses.removeAll(duplicatedNameCourse);
        }
    }

    @Override
    public List<CourseInfo> getMandatoryCourses() {
        return this.mandatoryCourses;
    }

}
