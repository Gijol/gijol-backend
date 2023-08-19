package com.gist.graduation.auth.application;

import com.gist.graduation.auth.dto.GoogleAuthBaseResponse;
import com.gist.graduation.auth.dto.GoogleSignUpRequest;
import com.gist.graduation.auth.infra.OAuthTokenVerifier;
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

    private final OAuthTokenVerifier googleAuthTokenVerifier;

    @Transactional
    public Long signUp(GoogleSignUpRequest request, String idToken) {
        final GoogleAuthBaseResponse googleAuthBaseResponse = googleAuthTokenVerifier.verify(idToken);
        final String email = googleAuthBaseResponse.getEmail();
        final String name = request.getName();
        final String studentId = request.getStudentId().trim();

        if (userRepository.existsUserByEmail(email)) throw new ApplicationException("이미 존재하는 회원입니다.");
        if (userRepository.existsUserByStudentId(studentId)) throw new ApplicationException("이미 존재하는 학번입니다.");

        final User user = User.builder()
                .email(email)
                .name(name)
                .studentId(studentId)
                .majorType(request.getMajorType())
                .userTakenCourses(request.toUserTakenCourseEntityList())
                .build();
        return userRepository.save(user).getId();
    }


    public AuthType findGoogleLoginType(String token) {
        GoogleAuthBaseResponse googleAuthBaseResponse = googleAuthTokenVerifier.verify(token);
        Optional<User> user = findUserFromToken(googleAuthBaseResponse);
        if (user.isEmpty()) return AuthType.SIGN_UP;
        return AuthType.SIGN_IN;
    }

    public Optional<User> findUserFromToken(GoogleAuthBaseResponse response) {
        final String email = response.getEmail();
        return userRepository.findUserByEmail(email);
    }

}
