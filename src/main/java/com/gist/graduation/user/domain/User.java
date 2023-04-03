package com.gist.graduation.user.domain;

import com.gist.graduation.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "users", indexes = {
        @Index(name = "idx_user_student_id", columnList = "student_id"),
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

    @Column(unique = true, nullable = false)
    private String studentId;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<UserTakenCourse> userTakenCourses = new ArrayList<>();

    // graduationStatus

    @Builder
    public User(String name, String email, String studentId, List<UserTakenCourse> userTakenCourses) {
        this.name = name;
        this.email = email;
        this.studentId = studentId;
        this.userTakenCourses = userTakenCourses;
    }

}
