package com.gist.graduation.requirment.domain.major;

import com.gist.graduation.course.domain.CourseInfo;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static com.gist.graduation.requirment.domain.constants.MajorMandatoryConstants.Biology.*;

@RequiredArgsConstructor
public enum BiologyMajor implements MajorInterface {

    FROM2021(List.of(18, 19, 20, 21, 22), List.of(BS2101, BS2102, BS2103, BS2103_1, BS2104, BS2104_1, BS3101, BS3105, BS3112));


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

    private static void removeDuplication(List<CourseInfo> lackOfMandatoryCourses) {
        List<CourseInfo> bioChemistry = List.of(BS2104, BS2104_1);
        List<CourseInfo> bioExperiment = List.of(BS2103, BS2103_1);

        removeEachDuplicate(lackOfMandatoryCourses, bioChemistry, BS2104);
        removeEachDuplicate(lackOfMandatoryCourses, bioExperiment, BS2103);
    }

    private static void removeEachDuplicate(List<CourseInfo> userTakenMandatoryCourses, List<CourseInfo> duplicatedNameCourse, CourseInfo oldCourse) {
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

    @Override
    public boolean contains(Integer studentId) {
        return this.studentId.contains(studentId);
    }
}
