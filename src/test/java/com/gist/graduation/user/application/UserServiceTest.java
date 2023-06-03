package com.gist.graduation.user.application;

import com.gist.graduation.fixture.Fixtures;
import com.gist.graduation.requirment.domain.GraduationRequirementStatus;
import com.gist.graduation.user.domain.User;
import com.gist.graduation.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void checkGraduationRequirementForUser() {
        //given
        final User user = Fixtures.createUser();
        userRepository.save(user);

        //when
        GraduationRequirementStatus graduationRequirementStatus = userService.checkGraduationRequirementForUser(user);

        assertThat(graduationRequirementStatus.getTotalCredits()).isEqualTo(10);
    }
}