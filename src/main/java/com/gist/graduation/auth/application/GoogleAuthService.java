package com.gist.graduation.auth.application;

import com.gist.graduation.auth.application.jwt.GoogleJWTProvider;
import com.gist.graduation.auth.application.jwt.LoginUser;
import com.gist.graduation.auth.dto.GoogleAuthRequest;
import com.gist.graduation.auth.dto.GoogleSignUpRequest;
import com.gist.graduation.config.exception.ApplicationException;
import com.gist.graduation.user.domain.User;
import com.gist.graduation.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GoogleAuthService {

    private final UserRepository userRepository;

    private final GoogleJWTProvider jwtProvider;

    @Transactional
    public Long signUp(GoogleSignUpRequest request) {
        final LoginUser loginUser = parseToLoginUserFromRequest(request);

        User user = User.builder()
                .email(loginUser.getEmail())
                .name(loginUser.getName())
                .studentId(request.getStudentId())
                .build();

        user.getUserTakenCourses().addAll(request.getUserTakenCourseList());
        return userRepository.save(user).getId();
    }


    public AuthType findGoogleLoginType(GoogleAuthRequest request) {
        final LoginUser loginUser = parseToLoginUserFromRequest(request);

        if (existUserByNameAndEmail(loginUser.getName(), loginUser.getEmail())) {
            return AuthType.SIGN_IN;
        }
        return AuthType.SIGN_UP;
    }

    public User loginGoogleAuth(GoogleAuthRequest request) {
        final LoginUser loginUser = parseToLoginUserFromRequest(request);

        return findUserByNameAndEmail(loginUser.getName(), loginUser.getEmail());
    }

    private LoginUser parseToLoginUserFromRequest(GoogleAuthRequest request) {
        String idToken = request.getIdToken();
        jwtProvider.verifyJwt(idToken);

        final LoginUser loginUser = jwtProvider.decodeToLoginUserByType(idToken);
        loginUser.verifyFromRequest(request);

        return loginUser;
    }

    private User findUserByNameAndEmail(String name, String email) {
        return userRepository.findUserByNameAndEmail(name, email)
                .orElseThrow(() -> new ApplicationException("존재하지 않는 사용자입니다."));
    }

    private boolean existUserByNameAndEmail(String name, String email) {
        return userRepository.existsUserByNameAndEmail(name, email);
    }
}
