package com.gist.graduation.user.domain;

import com.gist.graduation.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "users")
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

    // userTakenCoruses

    // graduationStatus

    @Builder
    public User(String name, String email, String studentId) {
        this.name = name;
        this.email = email;
        this.studentId = studentId;
    }
}
