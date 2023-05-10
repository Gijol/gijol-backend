package com.gist.graduation.auth.application;

import com.gist.graduation.auth.dto.GoogleIdTokenVerificationResponse;
import com.gist.graduation.auth.dto.GoogleSignUpRequest;
import com.gist.graduation.auth.infra.GoogleAuthTokenVerifier;
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

    private final GoogleAuthTokenVerifier googleAuthTokenVerifier;

    @Transactional
    public Long signUp(GoogleSignUpRequest request, String idToken) {
        GoogleIdTokenVerificationResponse googleIdTokenVerificationResponse = googleAuthTokenVerifier.verifyGoogleOAuth2IdToken(idToken);
        final String email = googleIdTokenVerificationResponse.getEmail();
        final String name = googleIdTokenVerificationResponse.getName();
        if (userRepository.existsUserByNameAndEmail(name, email)) throw new ApplicationException("이미 존재하는 회원입니다.");

        final User user = User.builder()
                .email(email)
                .name(name)
                .pictureUrl(googleIdTokenVerificationResponse.getPicture())
                .givenName(googleIdTokenVerificationResponse.getGiven_name())
                .familyName(googleIdTokenVerificationResponse.getFamily_name())
                .locale(googleIdTokenVerificationResponse.getLocale())
                .studentId(request.getStudentId())
                .userTakenCourses(request.toUserTakenCourseEntityList())
                .build();


        return userRepository.save(user).getId();
    }


    public AuthType findGoogleLoginType(String idToken) {
        GoogleIdTokenVerificationResponse response = googleAuthTokenVerifier.verifyGoogleOAuth2IdToken(idToken);
        Optional<User> user = findUserFromVerifiedIdTokenResponse(response);
        if (user.isEmpty()) return AuthType.SIGN_UP;
        return AuthType.SIGN_IN;
    }

    public Optional<User> findUserFromVerifiedIdTokenResponse(GoogleIdTokenVerificationResponse response) {
        String email = response.getEmail();
        String name = response.getName();
        return userRepository.findUserByNameAndEmail(name, email);
    }

}
