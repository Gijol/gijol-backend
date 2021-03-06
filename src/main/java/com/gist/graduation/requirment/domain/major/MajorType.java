package com.gist.graduation.requirment.domain.major;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.gist.graduation.exception.ApplicationException;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.TriConsumer;

@RequiredArgsConstructor
public enum MajorType {
    EC(EECSMajor::checkverified, 36),
    MA(MaterialScienceMajor::checkverified, 30),
    MC(MechanicalEngineeringMajor::checkverified, 36),
    EV(EnvironmentMajor::checkverified, 36),
    BS(BiologyMajor::checkverified, 36),
    PS(PhysicsMajor::checkverified, 36),
    CH(ChemistryMajor::checkverified, 36);

    public static final int MAJOR_MAXIMUM_CREDITS = 42;
    private final TriConsumer<UserTakenCoursesList, Major, Integer> checkMajorMandatory;
    private final Integer totalCredits;

    public void checkMajorStatus(UserTakenCoursesList inputUserTakenCoursesList, Major major, Integer studentId) {
        checkMajorMandatory.accept(inputUserTakenCoursesList, major, studentId);
        addCredits(major);
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
    public static MajorType fromMajorType(String major){
        for (MajorType majorType : MajorType.values()) {
            if (majorType.name().equals(major)) {
                return majorType;
            }
        }
        throw new ApplicationException("존재하지 않는 전공입니다.");
    }
}
