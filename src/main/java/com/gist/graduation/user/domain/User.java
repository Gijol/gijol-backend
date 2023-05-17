package com.gist.graduation.user.domain;

import com.gist.graduation.common.BaseEntity;
import com.gist.graduation.requirment.domain.GraduationRequirementStatus;
import com.gist.graduation.requirment.domain.major.MajorType;
import com.gist.graduation.user.taken_course.TakenCourse;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Table(name = "users", indexes = {
        @Index(name = "idx_user_email", columnList = "email"),
})
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Embedded
    private GoogleAdditionalInfo googleAdditionalInfo;

    @Column(name = "student_id", unique = true, nullable = false)
    private String studentId;

    @Column(name = "major_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private MajorType majorType;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<UserTakenCourse> userTakenCourses = new ArrayList<>();

    // graduationStatus

    @Builder
    public User(String name, String email, String pictureUrl, String givenName,
                MajorType majorType, String familyName, String locale,
                String studentId, List<UserTakenCourse> userTakenCourses) {
        this.name = name;
        this.email = email;
        this.majorType = majorType;
        this.googleAdditionalInfo = new GoogleAdditionalInfo(pictureUrl, givenName, familyName, locale);
        this.studentId = studentId;
        this.userTakenCourses = userTakenCourses;
    }

    public GraduationRequirementStatus checkGraduationStatus(){
        final GraduationRequirementStatus graduationRequirementStatus = new GraduationRequirementStatus();
        graduationRequirementStatus.checkGraduationRequirements(getStudentIdGroup(), this.toUserTakenCoursesList(), this.majorType);
        return graduationRequirementStatus;
    }

    public Integer getStudentIdGroup(){
        return Integer.parseInt(this.studentId.substring(studentId.length() - 6, studentId.length() - 4));
    }

    private UserTakenCoursesList toUserTakenCoursesList(){
        List<TakenCourse> takenCourses = this.userTakenCourses.stream()
                .map(UserTakenCourse::toTakenCourse)
                .collect(Collectors.toList());
        return new UserTakenCoursesList(takenCourses);
    }
}
