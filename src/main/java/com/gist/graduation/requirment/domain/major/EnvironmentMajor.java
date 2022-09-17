package com.gist.graduation.requirment.domain.major;

import com.gist.graduation.course.domain.CourseInfo;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.gist.graduation.requirment.domain.constants.MajorMandatoryConstants.Environment.*;

@RequiredArgsConstructor
public enum EnvironmentMajor implements MajorInterface {

    FROM2018(List.of(18, 19, 20, 21, 22), List.of(EV3101, EV3106, EV3111, EV4106, EV4107));

    private final List<Integer> studentId;
    private final List<CourseInfo> mandatoryCourses;

    @Override
    public List<CourseInfo> getMandatoryCourses() {
        return this.mandatoryCourses;
    }

    @Override
    public boolean contains(Integer studentId) {
        return this.studentId.contains(studentId);
    }

}
