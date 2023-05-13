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

    public GraduationRequirementStatus checkGraduationRequirementForUser(User user) {
        final GraduationRequirementStatus graduationRequirementStatus = user.checkGraduationStatus();
        return graduationRequirementStatus;
    }

    public UserTakenCoursesAndGradeResponse findUserTakenCourseAndAverageGrade(User user) {

        Map<YearAndSemester, List<UserTakenCourseResponse>> userTakenListByYearAndSemester = user.getUserTakenCourses()
                .stream()
                .collect(
                        Collectors.groupingBy(UserTakenCourse::getYearAndSemester,
                                Collectors.mapping(UserTakenCourseResponse::from, Collectors.toList())
                        )
                );

        List<UserTakenCourseBySemesterResponse> userTakenCourseBySemesterResponses = userTakenListByYearAndSemester.entrySet()
                .stream()
                .map(entry -> {
                    YearAndSemester key = entry.getKey();
                    List<UserTakenCourseResponse> value = entry.getValue();
                    BigDecimal averageGradeBySemester = calculateAverageGradeBySemester(value);
                    return new UserTakenCourseBySemesterResponse(key.getYear(), key.getSemester(), averageGradeBySemester, value);
                })
                .collect(Collectors.toList());

        BigDecimal averageGrade = calculateTotalAverageGrade(userTakenCourseBySemesterResponses);

        return new UserTakenCoursesAndGradeResponse(userTakenCourseBySemesterResponses, averageGrade);
    }

    private BigDecimal calculateTotalAverageGrade(List<UserTakenCourseBySemesterResponse> userTakenCourseBySemesterResponses) {
        return BigDecimal.ZERO;
    }


    private BigDecimal calculateAverageGradeBySemester(List<UserTakenCourseResponse> userTakenCourses) {
        return BigDecimal.ZERO;
    }

}
