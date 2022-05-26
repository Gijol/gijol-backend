package com.gist.graduation.utils;

import com.gist.graduation.exception.ApplicationException;
import com.gist.graduation.requirment.domain.etc.OthersEtc;
import com.gist.graduation.user.taken_course.TakenCourse;
import com.gist.graduation.user.taken_course.UserTakenCoursesList;
import com.gist.graduation.utils.filter.FailGrade;
import com.gist.graduation.utils.filter.Letter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.HttpStatus;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.gist.graduation.requirment.domain.etc.OthersEtc.COLLOQUIUM;

public class UserTakenCousrseParser {

    public static final int TYPE_CELL_NUM = 0;
    public static final int CODE_CELL_NUM = 1;
    public static final int COURSE_NAME_CELL_NUM = 3;
    public static final int CREDIT_CELL_NUM = 4;
    public static final int GRADE_CELL_NUM = 5;

    public static UserTakenCoursesList parseUserTakenCourse(File file) {
        try {
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
            return new UserTakenCoursesList(courseArray);
        } catch (Exception e) {
            throw new ApplicationException("파싱 오류입니다.", HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        }
    }

    public static Integer getStudentId(File file) throws IOException {
        Workbook workbook = new HSSFWorkbook(new FileInputStream(file));
        Sheet sheet = workbook.getSheetAt(0);
        // row 1 and cell 0 is filled with student id in grade.
        String studentId = sheet.getRow(1).getCell(0).getStringCellValue().strip();
        studentId = studentId.substring(studentId.length() - 6, studentId.length() - 4);
        if (Integer.parseInt(studentId) < 18) {
            throw new ApplicationException("지원하지 않는 학번입니다.", HttpStatus.METHOD_NOT_ALLOWED);
        }
        return Integer.valueOf(studentId);
    }

    private static boolean isDividedByYear(Row row) {
        return row.getCell(COURSE_NAME_CELL_NUM) != null && row.getCell(COURSE_NAME_CELL_NUM).getStringCellValue().contains("학기>");
    }

    private static boolean endOfCode(Row row) {
        return row.getCell(CODE_CELL_NUM).getStringCellValue().equals("[학사]");
    }

    private static boolean notExistCodeRow(Row row) {
        return row.getCell(CODE_CELL_NUM) == null || row.getCell(CODE_CELL_NUM).getStringCellValue().equals("Code") || row.getCell(CODE_CELL_NUM).getStringCellValue().isBlank();
    }

    private static void addTakenCourse(List<TakenCourse> courseArray, String year, String semester, Row row) {
        String courseName = row.getCell(COURSE_NAME_CELL_NUM).getStringCellValue();
        String grade = row.getCell(GRADE_CELL_NUM).getStringCellValue();
        String courseCode = row.getCell(CODE_CELL_NUM).getStringCellValue();
        String credit = row.getCell(CREDIT_CELL_NUM).getStringCellValue();
        TakenCourse takenCourse = new TakenCourse(Integer.parseInt(year), semester, row.getCell(TYPE_CELL_NUM).getStringCellValue(), courseName, courseCode, credit);


        if (courseArray.contains(takenCourse) && Letter.isLetter(grade) && !takenCourse.equals(COLLOQUIUM)) {
            courseArray.remove(takenCourse);
            courseArray.add(takenCourse);
            return;
        }

        if (!FailGrade.isFail(grade)) {
            courseArray.add(takenCourse);
        }


    }

}
