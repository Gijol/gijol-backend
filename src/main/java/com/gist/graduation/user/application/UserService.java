package com.gist.graduation.user.application;

import com.gist.graduation.requirment.domain.GraduationRequirementStatus;
import com.gist.graduation.user.domain.User;
import com.gist.graduation.user.domain.UserTakenCourse;
import com.gist.graduation.user.domain.vo.YearAndSemester;
import com.gist.graduation.user.dto.UserTakenCoursesAndGradeResponse;
import com.gist.graduation.user.dto.UserTakenCoursesAndGradeResponse.UserTakenCourseBySemesterResponse;
import com.gist.graduation.user.dto.UserTakenCoursesAndGradeResponse.UserTakenCourseResponse;
import com.gist.graduation.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final GraduationCalculator graduationCalculator;

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

        return new UserTakenCoursesAndGradeResponse(userTakenCourseBySemesterResponses, averageGrade);
    }
}
