package com.gist.graduation.user.application;

import com.gist.graduation.requirment.domain.GraduationRequirementStatus;
import com.gist.graduation.user.domain.User;
import com.gist.graduation.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public GraduationRequirementStatus checkGraduationRequirementForUser(User user){
        final GraduationRequirementStatus graduationRequirementStatus = user.checkGraduationStatus();
        return graduationRequirementStatus;
    }

}
