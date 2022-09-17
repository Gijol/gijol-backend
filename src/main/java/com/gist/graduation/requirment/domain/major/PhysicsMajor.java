package com.gist.graduation.requirment.domain.major;

import com.gist.graduation.course.domain.CourseInfo;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.gist.graduation.requirment.domain.constants.MajorMandatoryConstants.Physics.*;

@RequiredArgsConstructor
public enum PhysicsMajor implements MajorInterface {

    FROM2018(List.of(18, 19, 20, 21, 22), List.of(PS2101, PS2102, PS2103, PS3103, PS3104, PS3105, PS3106, PS3107));

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
