package com.gist.graduation.requirment.domain.major;

import com.gist.graduation.course.domain.CourseInfo;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.gist.graduation.requirment.domain.constants.MajorMandatoryConstants.MaterialScience.*;

@RequiredArgsConstructor
public enum MaterialScienceMajor implements MajorInterface {

    FROM2018(List.of(18, 19, 20, 21, 22), List.of(MA2101, MA2102, MA2103, MA2104, MA3104, MA3105));

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
