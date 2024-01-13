package com.gist.graduation.requirment.domain.major;

import com.gist.graduation.course.domain.CourseInfo;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.gist.graduation.requirment.domain.constants.MajorMandatoryConstants.EECS.EC3101;
import static com.gist.graduation.requirment.domain.constants.MajorMandatoryConstants.EECS.EC3102;

@RequiredArgsConstructor
public enum EECSMajor implements MajorInterface {

    FROM2018(List.of(18, 19, 20, 21, 22, 23, 24), List.of(EC3101, EC3102));

    private final List<Integer> studentId;
    private final List<CourseInfo> mandatoryCourses;

    @Override
    public void addLackOfMandatoryCourses(Major major, List<CourseInfo> mandatoryCourses) {
        UserTakenCoursesList userTakenCourses = major.getUserTakenCoursesList();

        if (userTakenCourses.notExistAny(mandatoryCourses)) {
            major.addMessage(String.format("%s 혹은 %s 수강해야 합니다.", mandatoryCourses.get(0), mandatoryCourses.get(1)));
        }
    }

    @Override
    public List<CourseInfo> getMandatoryCourses() {
        return this.mandatoryCourses;
    }


}
