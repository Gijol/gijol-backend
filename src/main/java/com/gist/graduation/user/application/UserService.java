package com.gist.graduation.user.application;

import com.gist.graduation.config.exception.ApplicationException;
import com.gist.graduation.requirment.domain.GraduationRequirementStatus;
import com.gist.graduation.requirment.domain.major.MajorType;
import com.gist.graduation.user.domain.User;
import com.gist.graduation.user.domain.UserTakenCourse;
import com.gist.graduation.user.domain.vo.YearAndSemester;
import com.gist.graduation.user.dto.UserInfoResponse;
import com.gist.graduation.user.dto.UserTakenCoursesAndGradeResponse;
import com.gist.graduation.user.dto.UserTakenCoursesAndGradeResponse.UserTakenCourseBySemesterResponse;
import com.gist.graduation.user.dto.UserTakenCoursesAndGradeResponse.UserTakenCourseResponse;
import com.gist.graduation.user.dto.UserTakenCoursesRequest;
import com.gist.graduation.user.repository.UserRepository;
import com.gist.graduation.user.repository.UserTakenCourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final UserTakenCourseRepository userTakenCourseRepository;
    private final GraduationCalculator graduationCalculator;

    public GraduationRequirementStatus checkGraduationRequirementForUser(Long userId) {
        final User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApplicationException("존재하지 않는 유저입니다."));
        return user.checkGraduationStatus();
    }

    public GraduationRequirementStatus checkGraduationRequirementForUser(User user) {
        final GraduationRequirementStatus graduationRequirementStatus = user.checkGraduationStatus();
        return graduationRequirementStatus;
    }

    public UserTakenCoursesAndGradeResponse findUserTakenCourseAndAverageGrade(User user) {
        List<UserTakenCourse> userTakenCourses = user.getUserTakenCourses();

        Map<YearAndSemester, List<UserTakenCourse>> userTakenListByYearAndSemester = userTakenCourses
                .stream()
                .collect(Collectors.groupingBy(UserTakenCourse::getYearAndSemester));

        List<UserTakenCourseBySemesterResponse> userTakenCourseBySemesterResponses = userTakenListByYearAndSemester.entrySet()
                .stream()
                .map(entry -> {
                    YearAndSemester key = entry.getKey();
                    List<UserTakenCourse> value = entry.getValue();
                    BigDecimal averageGradeBySemester = graduationCalculator.calculateTotalAverageGrade(value);
                    return new UserTakenCourseBySemesterResponse(key.getYear(), key.getSemester(), averageGradeBySemester, UserTakenCourseResponse.listFrom(value));
                })
                .collect(Collectors.toList());

        BigDecimal averageGrade = graduationCalculator.calculateTotalAverageGrade(userTakenCourses);
        final int totalCredit = userTakenCourses.stream()
                .mapToInt(UserTakenCourse::getCredit)
                .sum();

        return new UserTakenCoursesAndGradeResponse(userTakenCourseBySemesterResponses, averageGrade, totalCredit);
    }

    @Transactional
    public void updateMajor(User user, MajorType majorType) {
        user.updateMajorType(majorType);
    }

    @Transactional
    public void updateTakenCourses(User user, UserTakenCoursesRequest userTakenCoursesRequest) {
        userTakenCourseRepository.deleteByUserId(user.getId());
        userTakenCourseRepository.saveAll(userTakenCoursesRequest.toUserTakenCourseEntityList(user));
        userRepository.updateUserById(userTakenCoursesRequest.getStudentId(), user.getId());
    }

    @Transactional
    public void updateName(User user, String name) {
        user.updateName(name);
    }

    public UserInfoResponse getUserInfo(User user) {
        return UserInfoResponse.of(user);
    }

    @Transactional
    public void signOut(Long userId) {
        userTakenCourseRepository.deleteByUserId(userId);
        userRepository.deleteUserById(userId);
    }
}
