package com.gist.graduation.user.domain.vo;

import com.gist.graduation.config.exception.ApplicationException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum LetterGrade {

    A_PLUS("A+", BigDecimal.valueOf(4.5)),
    A_ZERO("A0", BigDecimal.valueOf(4.0)),
    B_PLUS("B+", BigDecimal.valueOf(3.5)),
    B_ZERO("B0", BigDecimal.valueOf(3.0)),
    C_PLUS("C+", BigDecimal.valueOf(2.5)),
    C_ZERO("C0", BigDecimal.valueOf(2.0)),
    D_PLUS("D+", BigDecimal.valueOf(1.5)),
    D_ZERO("D0", BigDecimal.valueOf(1.0)),
    F("F", BigDecimal.valueOf(0.0));

    private final String grade;
    private final BigDecimal gradePoint;

    public static BigDecimal getGradePointByGrade(String grade){
        return Arrays.stream(LetterGrade.values())
                .filter(letterGrade -> letterGrade.grade.equals(grade))
                .findFirst()
                .orElseThrow(() -> new ApplicationException("해당하는 학점이 없습니다."))
                .getGradePoint();
    }
}
