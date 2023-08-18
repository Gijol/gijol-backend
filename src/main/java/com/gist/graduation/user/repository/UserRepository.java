package com.gist.graduation.user.repository;

import com.gist.graduation.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByNameAndEmail(String name, String email);

    Optional<User> findUserByEmail(String email);


    boolean existsUserByNameAndEmail(String name, String email);

    boolean existsUserByEmail(String email);

    boolean existsUserByStudentId(String studentId);

}
