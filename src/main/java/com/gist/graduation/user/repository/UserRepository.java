package com.gist.graduation.user.repository;

import com.gist.graduation.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByNameAndEmail(String name, String email);

    Optional<User> findUserByEmail(String email);

    @Modifying
    @Query("UPDATE User u SET u.studentId = :studentId WHERE u.id = :id AND u.updatedAt = now() ")
    void updateUserById(@Param("studentId") String studentId, @Param("id") Long id);


    boolean existsUserByNameAndEmail(String name, String email);

    boolean existsUserByEmail(String email);

    boolean existsUserByStudentId(String studentId);

}
