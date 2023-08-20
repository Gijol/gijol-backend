package com.gist.graduation.user.domain;

import com.gist.graduation.common.BaseEntity;
import com.gist.graduation.config.exception.ApplicationException;
import com.gist.graduation.requirment.domain.GraduationRequirementStatus;
import com.gist.graduation.requirment.domain.major.MajorType;
import com.gist.graduation.user.taken_course.TakenCourse;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;
import com.gist.graduation.utils.converter.AesConverter;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;
import org.springframework.util.StringUtils;

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
@Where(clause = "deleted_at is not null")
public class User extends BaseEntity {

    @Column(name = "name")
    @Convert(converter = AesConverter.class)
    private String name;

    @Column(name = "email")
    @Convert(converter = AesConverter.class)
    private String email;

    @Column(name = "student_id")
    @Convert(converter = AesConverter.class)
    private String studentId;

    @Column(name = "major_type")
    @Enumerated(EnumType.STRING)
    private MajorType majorType;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<UserTakenCourse> userTakenCourses = new ArrayList<>();

    // graduationStatus

    @Builder
    public User(String name, String email,
                MajorType majorType,
                String studentId, List<UserTakenCourse> userTakenCourses) {
        this.name = name;
        this.email = email;
        this.majorType = majorType;
        this.studentId = studentId;
        this.userTakenCourses = userTakenCourses.stream()
                .peek(userTakenCourse -> userTakenCourse.setUser(this))
                .collect(Collectors.toList());
    }

    public GraduationRequirementStatus checkGraduationStatus() {
        final GraduationRequirementStatus graduationRequirementStatus = new GraduationRequirementStatus();
        graduationRequirementStatus.checkGraduationRequirements(getStudentIdGroup(), this.toUserTakenCoursesList(), this.majorType);
        return graduationRequirementStatus;
    }

    public Integer getStudentIdGroup() {
        return Integer.parseInt(this.studentId.substring(studentId.length() - 6, studentId.length() - 4));
    }

    public void updateMajorType(MajorType majorType) {
        this.majorType = majorType;
    }

    private UserTakenCoursesList toUserTakenCoursesList() {
        List<TakenCourse> takenCourses = this.userTakenCourses.stream()
                .map(UserTakenCourse::toTakenCourse)
                .collect(Collectors.toList());
        return new UserTakenCoursesList(takenCourses);
    }

    public void updateTakenCourses(List<UserTakenCourse> userTakenCourseEntityList) {
        this.userTakenCourses = userTakenCourseEntityList.stream()
                .peek(userTakenCourse -> userTakenCourse.setUser(this))
                .collect(Collectors.toList());
    }


    public void updateStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void updateName(String name) {
        if (!StringUtils.hasLength(name)) {
            throw new ApplicationException("이름은 공백일 수 없습니다.");
        }
        this.name = name;
    }

    @Override
    public void clearData() {
        super.clearData();
        this.majorType = null;
        this.name = null;
        this.email = null;
        this.studentId = null;

    }
}
