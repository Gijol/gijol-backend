package com.gist.graduation.auth.application;

import com.gist.graduation.config.exception.ApplicationException;
import com.gist.graduation.user.domain.User;
import com.gist.graduation.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public User findUserByNameAndEmail(String name, String email) {
        return userRepository.findUserByNameAndEmail(name, email)
                .orElseThrow(() -> new ApplicationException("존재하지 않는 사용자입니다."));
    }

}
