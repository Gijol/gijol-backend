package com.gist.graduation.utils;

import com.gist.graduation.course.RegisteredCourse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CourseListParser {

    public static final int INDEX = 0;
    public static final int YEAR_CELL_NUMBER = 1;
    public static final int SEMESTER_CELL_NUMBER = 2;
    public static final int CODE_CELL_NUMBER = 4;
    public static final int COUSER_NAME_CELL_NUMBER = 5;
    public static final int CREDIT_CELL_NUMBER = 11;

    @Bean
    public List<RegisteredCourse> getCourseList() throws IOException {
        ClassPathResource undergraduateResource = new ClassPathResource("course-information/course_information_undergraduate.xls");
        ClassPathResource graduateResource = new ClassPathResource("course-information/course_information_graduate.xls");
        List<RegisteredCourse> undergradCourses = parseCourseExcelFile(undergraduateResource);
        List<RegisteredCourse> gradCourses = parseCourseExcelFile(graduateResource);
        for (RegisteredCourse undergradCours : undergradCourses) {
            for (RegisteredCourse gradCours : gradCourses) {
                if (gradCours.getYear() == undergradCours.getYear() && gradCours.getSemester().equals(undergradCours.getSemester())) {
                    undergradCours.getCourses().addAll(gradCours.getCourses());
                }
            }
        }

        return undergradCourses;
    }

    private List<RegisteredCourse> parseCourseExcelFile(ClassPathResource resource) throws IOException {
        File file = resource.getFile();
        Workbook workbook = new HSSFWorkbook(new FileInputStream(file));
        Sheet sheet = workbook.getSheetAt(0);
        removeDuplicate(sheet);

        int year = 0;
        String semester = "";
        RegisteredCourse registeredCourse = null;
        List<RegisteredCourse> result = new ArrayList<>();

        for (Row row : sheet) {
            if (!row.getCell(INDEX).getStringCellValue().matches("[0-9]+")) {
                continue;
            }

            String yearString = row.getCell(YEAR_CELL_NUMBER).getStringCellValue();
            String semesterString = row.getCell(SEMESTER_CELL_NUMBER).getStringCellValue();

            if (year != Integer.parseInt(yearString) || !semester.equals(semesterString)) {
                year = Integer.parseInt(yearString);
                semester = semesterString;
                registeredCourse = new RegisteredCourse(year, semester, new ArrayList<>());
                result.add(registeredCourse);
            }

            String courseCode = row.getCell(CODE_CELL_NUMBER).getStringCellValue().split("-")[0];
            String courseName = row.getCell(COUSER_NAME_CELL_NUMBER).getStringCellValue();
            String creditString = row.getCell(CREDIT_CELL_NUMBER).getStringCellValue();
            creditString = creditString.substring(creditString.length() - 1);
        }
        return result;
    }

    private void removeDuplicate(Sheet sheet) {
        List<String> courseLists = new ArrayList<>();
        List<Row> toRemoveRows = new ArrayList<>();

        for (Row row : sheet) {
            String code = row.getCell(CODE_CELL_NUMBER).getStringCellValue().split("-")[0];
            String year = row.getCell(YEAR_CELL_NUMBER).getStringCellValue();
            String semester = row.getCell(SEMESTER_CELL_NUMBER).getStringCellValue();
            String courseInfo = String.format("%s%s%s", year, semester, code);
            if (courseLists.contains(courseInfo)) {
                toRemoveRows.add(row);
                continue;
            }
            courseLists.add(courseInfo);
        }

        for (Row row : toRemoveRows) {
            sheet.removeRow(row);
        }
    }
}
