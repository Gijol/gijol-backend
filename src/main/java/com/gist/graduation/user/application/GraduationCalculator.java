package com.gist.graduation.user.application;

import com.gist.graduation.user.domain.UserTakenCourse;
import com.gist.graduation.user.domain.vo.LetterGrade;
import com.gist.graduation.user.dto.UserTakenCoursesAndGradeResponse.UserTakenCourseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GraduationCalculator {

    public BigDecimal calculateTotalAverageGrade(List<UserTakenCourse> userTakenCourses) {
        BigDecimal totalCreditAndGrade = userTakenCourses.stream()
                .map(UserTakenCourse::multiplyCreditAndGrade)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalCredit = userTakenCourses.stream()
                .map(UserTakenCourse::getCredit)
                .map(BigDecimal::new)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return totalCreditAndGrade.divide(totalCredit, 2, RoundingMode.FLOOR);
    }
}
