package com.gist.graduation.user.application;

import com.gist.graduation.user.domain.UserTakenCourse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GraduationCalculator {
    private static final List<String> nonLetterGrade = List.of("S", "U", "W");

    public BigDecimal calculateTotalAverageGrade(List<UserTakenCourse> userTakenCourses) {
        BigDecimal totalCreditAndGrade = userTakenCourses.stream()
                .filter(s -> !nonLetterGrade.contains(s.getGrade()))
                .map(UserTakenCourse::multiplyCreditAndGrade)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalCredit = userTakenCourses.stream()
                .filter(s -> !nonLetterGrade.contains(s.getGrade()))
                .map(UserTakenCourse::getCredit)
                .map(BigDecimal::new)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return totalCreditAndGrade.divide(totalCredit, 2, RoundingMode.FLOOR);
    }
}
