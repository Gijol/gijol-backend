package com.gist.graduation.utils;

import com.gist.graduation.course.CoreCourseInfo;
import com.gist.graduation.course.Course;
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
    public List<Course> getCourseList() throws IOException {
        ClassPathResource undergraduateResource = new ClassPathResource("course-information/course_information_undergraduate.xls");
        ClassPathResource graduateResource = new ClassPathResource("course-information/course_information_graduate.xls");
        List<Course> undergradCourses = parseCourseExcelFile(undergraduateResource);
        List<Course> gradCourses = parseCourseExcelFile(graduateResource);
        for (Course undergradCours : undergradCourses) {
            for (Course gradCours : gradCourses) {
                if (gradCours.getYear() == undergradCours.getYear() && gradCours.getSemester().equals(undergradCours.getSemester())){
                    undergradCours.getCoreCourseInfos().addAll(gradCours.getCoreCourseInfos());
                }
            }
        }

        return undergradCourses;
    }

    private List<Course> parseCourseExcelFile(ClassPathResource resource) throws IOException {
        File file = resource.getFile();
        Workbook workbook = new HSSFWorkbook(new FileInputStream(file));
        Sheet sheet = workbook.getSheetAt(0);
        removeDuplicate(sheet);

        int year = 0;
        String semester = "";
        Course course = null;
        List<Course> result = new ArrayList<>();

        for (Row row : sheet) {
            if (!row.getCell(INDEX).getStringCellValue().matches("[0-9]+")) {
                continue;
            }

            String yearString = row.getCell(YEAR_CELL_NUMBER).getStringCellValue();
            String semesterString = row.getCell(SEMESTER_CELL_NUMBER).getStringCellValue();

            if (year != Integer.parseInt(yearString) || !semester.equals(semesterString)) {
                year = Integer.parseInt(yearString);
                semester = semesterString;
                course = new Course(year, semester, new ArrayList<>());
                result.add(course);
            }

//            System.out.print(" " + row.getCell(YEAR_CELL_NUMBER));
//            System.out.print(" " + row.getCell(SEMESTER_CELL_NUMBER));
            String courseIndex = row.getCell(INDEX).getStringCellValue();
            String courseCode = row.getCell(CODE_CELL_NUMBER).getStringCellValue().split("-")[0];
//            System.out.print(" " + courseCode);
            String courseName = row.getCell(COUSER_NAME_CELL_NUMBER).getStringCellValue();
//            System.out.print(" " + courseName);
            String creditString = row.getCell(CREDIT_CELL_NUMBER).getStringCellValue();
            creditString = creditString.substring(creditString.length()-1);
//            System.out.print(" " + creditString);
//            System.out.println();
            course.getCoreCourseInfos().add(new CoreCourseInfo(courseIndex, courseCode,courseName,Integer.parseInt(creditString)));
        }
//        System.out.println(course);
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
