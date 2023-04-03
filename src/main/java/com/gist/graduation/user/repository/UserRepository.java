package com.gist.graduation.user.repository;

import com.gist.graduation.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByNameAndEmail(String name, String email);

    boolean existUserByNameAndEmail(String name, String email);

}
