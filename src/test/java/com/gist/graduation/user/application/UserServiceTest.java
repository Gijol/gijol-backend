package com.gist.graduation.user.application;

import com.gist.graduation.fixture.Fixtures;
import com.gist.graduation.requirment.domain.GraduationRequirementStatus;
import com.gist.graduation.user.domain.User;
import com.gist.graduation.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void checkGraduationRequirementForUser() {
        //given
        final User user = Fixtures.createUser();
        User savedUser = userRepository.save(user);

        //when
        User foundUser = userRepository.findById(savedUser.getId()).orElseThrow(IllegalArgumentException::new);
        GraduationRequirementStatus graduationRequirementStatus = userService.checkGraduationRequirementForUser(foundUser);

        // then
        assertThat(graduationRequirementStatus.getTotalCredits()).isEqualTo(10);
    }

}