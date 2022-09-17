package com.gist.graduation.requirment.domain.major;

import com.gist.graduation.course.domain.CourseInfo;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static com.gist.graduation.requirment.domain.constants.MajorMandatoryConstants.Chemistry.*;

@RequiredArgsConstructor
public enum ChemistryMajor implements MajorInterface {

    FROM2018(List.of(18, 19, 20, 21, 22), List.of(CH2101, CH2102, CH2103, CH2104, CH2105, CH3106, CH3107));

    private final List<Integer> studentId;
    private final List<CourseInfo> mandatoryCourses;


    @Override
    public void addLackOfMandatoryCourses(Major major, List<CourseInfo> mandatoryCourses) {
        UserTakenCoursesList userTakenCourses = major.getUserTakenCoursesList();

        List<CourseInfo> lackOfMandatoryCourses = mandatoryCourses.stream()
                .filter(userTakenCourses::notExist)
                .collect(Collectors.toList());

        checkException(lackOfMandatoryCourses);

        for (CourseInfo lackOfMandatoryCourse : lackOfMandatoryCourses) {
            major.addMessage(String.format("%s를 수강해야 합니다.", lackOfMandatoryCourse.toString()));
        }
    }

    private void checkException(List<CourseInfo> userTakenMandatoryCourses) {
        List<CourseInfo> physicalChemistryA = List.of(CH2102, CH3104);
        List<CourseInfo> physicalChemistryB = List.of(GS2202, CH2104);
        if (userTakenMandatoryCourses.containsAll(physicalChemistryA)) {
            userTakenMandatoryCourses.remove(CH3104);
        }
        if (userTakenMandatoryCourses.containsAll(physicalChemistryB)) {
            userTakenMandatoryCourses.remove(GS2202);
        }
    }

    @Override
    public List<CourseInfo> getMandatoryCourses() {
        return this.mandatoryCourses;
    }

    @Override
    public boolean contains(Integer studentId) {
        return this.studentId.contains(studentId);
    }

}
