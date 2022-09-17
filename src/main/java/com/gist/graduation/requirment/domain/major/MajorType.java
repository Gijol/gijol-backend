package com.gist.graduation.requirment.domain.major;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.gist.graduation.course.domain.CourseInfo;
import com.gist.graduation.exception.ApplicationException;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public enum MajorType {
    EC(EECSMajor.values(), 36),
    MA(MaterialScienceMajor.values(), 30),
    MC(MechanicalEngineeringMajor.values(), 36),
    EV(EnvironmentMajor.values(), 36),
    BS(BiologyMajor.values(), 36),
    PS(PhysicsMajor.values(), 36),
    CH(ChemistryMajor.values(), 36);

    public static final int MAJOR_MAXIMUM_CREDITS = 42;
    private final MajorInterface[] majorInterfaces;
    private final Integer totalCredits;

    public void doCheck(UserTakenCoursesList inputUserTakenCoursesList, Major major, Integer studentId) {
        for (MajorInterface majorInterface : this.majorInterfaces) {
            if (majorInterface.contains(studentId)) {
                List<CourseInfo> mandatoryCourses = majorInterface.getMandatoryCourses();

                majorInterface.checkMandatoryCourses(inputUserTakenCoursesList, major, mandatoryCourses);
                majorInterface.addLackOfMandatoryCourses(major, mandatoryCourses);
                majorInterface.checkElectiveCourses(inputUserTakenCoursesList, major, mandatoryCourses, this.name());
                addCredits(major);
                return;
            }
        }
        throw new IllegalArgumentException("확인할 수 없는 학번입니다.");
    }

    private void addCredits(Major major) {
        Integer takenCredits = major.getUserTakenCoursesList().sumCreditOfCourses();
        major.addCredit(Math.min(MAJOR_MAXIMUM_CREDITS, takenCredits));
        major.setMinConditionCredits(this.totalCredits);

        if (takenCredits < totalCredits) {
            if (major.getMessages().isEmpty()) {
                major.addMessage(String.format("전공과목을 %d학점을 더 들어야 합니다.", (totalCredits - takenCredits)));
                return;
            }

            major.addMessage(String.format("전공필수 과목을 포함해 %d학점을 더 들어야 합니다.", (totalCredits - takenCredits)));
        }
    }

    @JsonCreator
    public static MajorType fromMajorType(String major) {
        for (MajorType majorType : MajorType.values()) {
            if (majorType.name().equals(major)) {
                return majorType;
            }
        }
        throw new ApplicationException("존재하지 않는 전공입니다.");
    }
}
