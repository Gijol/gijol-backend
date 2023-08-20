package com.gist.graduation.user.domain.vo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class YearAndSemester {

    @Column(name = "year")
    private Integer year;

    @Column(name = "semester")
    private String semester;

    public YearAndSemester(int year, String semester) {
        this.year = year;
        this.semester = semester;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof YearAndSemester)) return false;
        YearAndSemester that = (YearAndSemester) o;
        return Objects.equals(year, that.year) && Objects.equals(semester, that.semester);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, semester);
    }
}
