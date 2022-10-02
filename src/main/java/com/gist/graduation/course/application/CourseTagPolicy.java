package com.gist.graduation.course.application;

import com.gist.graduation.course.domain.Course;
import com.gist.graduation.course.domain.CourseInfo;
import com.gist.graduation.course.domain.CourseTag;
import com.gist.graduation.course.domain.CourseTagType;
import com.gist.graduation.utils.HumanitiesListParser;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.gist.graduation.requirment.domain.constants.MajorMandatoryConstants.Biology.BS;
import static com.gist.graduation.requirment.domain.constants.MajorMandatoryConstants.Chemistry.CH;
import static com.gist.graduation.requirment.domain.constants.MajorMandatoryConstants.EECS.EC;
import static com.gist.graduation.requirment.domain.constants.MajorMandatoryConstants.Environment.EV;
import static com.gist.graduation.requirment.domain.constants.MajorMandatoryConstants.MaterialScience.MA;
import static com.gist.graduation.requirment.domain.constants.MajorMandatoryConstants.MechanicalEngineering.MC;
import static com.gist.graduation.requirment.domain.constants.MajorMandatoryConstants.Physics.PS;

@Component
public class CourseTagPolicy {
    private static final List<String> MAJOR_COURSE_CODE = List.of(EC, MA, CH, PS, EV, BS, MC);

    public void tagAllCourses(List<Course> courses) {
        tagHUS(courses);
        tagPPE(courses);
        tagMajor(courses);
    }


    private void tagHUS(List<Course> courses) {
        List<CourseInfo> husCoursesList = HumanitiesListParser.getHUSCoursesList();
        courses.stream()
                .filter(s -> s.belongToCourseInfo(husCoursesList))
                .forEach(s -> s.addTag(new CourseTag(CourseTagType.HUS, s)));
    }

    private void tagPPE(List<Course> courses) {
        List<CourseInfo> husCoursesList = HumanitiesListParser.getPPECoursesList();
        courses.stream()
                .filter(s -> s.belongToCourseInfo(husCoursesList))
                .forEach(s -> s.addTag(new CourseTag(CourseTagType.PPE, s)));
    }

    private void tagMajor(List<Course> courses) {
        courses.stream()
                .filter(s -> s.getCourseInfo().belongToCoursesCode(MAJOR_COURSE_CODE))
                .forEach(s -> s.addTag(new CourseTag(CourseTagType.전공, s)));
    }

}
