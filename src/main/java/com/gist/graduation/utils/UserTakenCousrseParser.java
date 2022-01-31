package com.gist.graduation.utils;

import com.gist.graduation.user.taken_course.TakenCourse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserTakenCousrseParser {

    public static final int TYPE_CELL_NUM = 0;
    public static final int CODE_CELL_NUM = 1;
    public static final int COURSE_NAME_CELL_NUM = 3;
    public static final int CREDIT_CELL_NUM = 4;
    public static final int GRADE_CELL_NUM = 5;

    public List<TakenCourse> parseUserTakenCousrse(File file) throws IOException {
        Workbook workbook = new HSSFWorkbook(new FileInputStream(file));
        Sheet sheet = workbook.getSheetAt(0);

        List<TakenCourse> courseArray = new ArrayList<>();
        String year = "";
        String semester = "";
        for (Row row : sheet) {
            if (isDividedByYear(row)) {
                String stringCellValue = row.getCell(3).getStringCellValue();
                stringCellValue = stringCellValue.substring(1, stringCellValue.length() - 1);
                year = stringCellValue.split("/")[0];
                semester = stringCellValue.split("/")[1];
                continue;
            }

            if (notExistCodeRow(row)) {
                continue;
            }
            if (endOfCode(row)) {
                break;
            }

            addTakenCourse(courseArray, year, semester, row);
        }
        return courseArray;
    }

    private boolean isDividedByYear(Row row) {
        return row.getCell(COURSE_NAME_CELL_NUM) != null && row.getCell(COURSE_NAME_CELL_NUM).getStringCellValue().contains("학기>");
    }

    private boolean endOfCode(Row row) {
        return row.getCell(CODE_CELL_NUM).getStringCellValue().equals("[학사]");
    }

    private boolean notExistCodeRow(Row row) {
        return row.getCell(CODE_CELL_NUM) == null || row.getCell(CODE_CELL_NUM).getStringCellValue().equals("Code") || row.getCell(CODE_CELL_NUM).getStringCellValue().isBlank();
    }

    private void addTakenCourse(List<TakenCourse> courseArray, String year, String semester, Row row) {
        String courseName = row.getCell(COURSE_NAME_CELL_NUM).getStringCellValue();
        String grade = row.getCell(GRADE_CELL_NUM).getStringCellValue();
        String courseCode = row.getCell(CODE_CELL_NUM).getStringCellValue();
        String credit = row.getCell(CREDIT_CELL_NUM).getStringCellValue();

        if (!FailGrade.isFail(grade)) {
            courseArray.add(new TakenCourse(Integer.parseInt(year), semester, row.getCell(TYPE_CELL_NUM).getStringCellValue(), courseName, courseCode, credit));
        }
    }

}
