package com.gist.graduation.auth.application;

import com.gist.graduation.auth.dto.GoogleAuthBaseResponse;
import com.gist.graduation.auth.dto.GoogleSignUpRequest;
import com.gist.graduation.auth.infra.GoogleOAuthTokenVerifier;
import com.gist.graduation.config.exception.ApplicationException;
import com.gist.graduation.user.domain.User;
import com.gist.graduation.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GoogleAuthService {

    private final UserRepository userRepository;

    private final GoogleOAuthTokenVerifier googleAuthTokenVerifier;

    @Transactional
    public Long signUp(GoogleSignUpRequest request, String token) {
        final String name = request.getName();
        final String studentId = request.getStudentId().trim();
        final GoogleAuthBaseResponse googleAuthBaseResponse = googleAuthTokenVerifier.verify(token);
        final String email = googleAuthBaseResponse.getEmail();

        if (userRepository.existsUserByEmail(email)) throw new ApplicationException("이미 존재하는 회원입니다.");

        final User user = User.builder()
                .email(email)
                .name(name)
                .studentId(studentId)
                .majorType(request.getMajorType())
                .userTakenCourses(request.toUserTakenCourseEntityList())
                .build();
        return userRepository.save(user).getId();
    }


    public Boolean isNewUser(String token) {
        GoogleAuthBaseResponse googleAuthBaseResponse = googleAuthTokenVerifier.verify(token);
        Optional<User> user = findUserFromToken(googleAuthBaseResponse);
        return user.isPresent();
    }

    public Optional<User> findUserFromToken(GoogleAuthBaseResponse response) {
        final String email = response.getEmail();
        return userRepository.findUserByEmail(email);
    }

}
