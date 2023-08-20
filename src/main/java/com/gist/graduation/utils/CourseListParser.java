package com.gist.graduation.utils;

import com.gist.graduation.course.domain.CourseInfo;
import com.gist.graduation.course.domain.rawcourse.RawCourse;
import com.gist.graduation.config.exception.ApplicationException;
import com.gist.graduation.requirment.domain.constants.HumanitiesExceptionConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;
import java.util.stream.Collectors;


@Slf4j
public class CourseListParser {

    public static final int INDEX = 0;
    public static final int YEAR_CELL_NUMBER = 1;
    public static final int SEMESTER_CELL_NUMBER = 2;
    public static final int CODE_CELL_NUMBER = 4;
    public static final int COURSE_NAME_CELL_NUMBER = 5;
    public static final int COURSE_TYPE_CELL_NUMBER = 6;
    public static final int LIBERAL_ART_TYPE_CELL_NUMBER = 7;
    public static final int PROFESSOR_NUMBER = 9;
    public static final int CREDIT_CELL_NUMBER = 11;
    public static final int TIME_TABLE_NUMBER = 13;
    public static final int LOCATION_NUMBER = 14;
    public static final int PREREQUISITE_NUMBER = 17;

    public static List<RawCourse> parseToRawCourse(File file) {
        Sheet sheet = convertFileToSheet(file);
        return RawCourse.from(parseRegistrationCourse(sheet));
    }

    public static List<RegisteredCourse> getCourseList(File file) {
        List<RegisteredCourse> undergradCourses = parseCourseExcelFileToRegistrationCourseWithoutDuplication(file);
        return undergradCourses;
    }

    public static List<RegisteredCourse> getCourseList() {
        FileResourceUtils fileResourceUtils = new FileResourceUtils();
        File file = fileResourceUtils.convertPathResourceToCourseListFileAtServer();
        return getCourseList(file);
    }

    public static List<CourseInfo> getHumanitiesWithoutGSC() {
        List<CourseInfo> humanitiesCoursesList = getHumanitiesCoursesList();
        HumanitiesExceptionConstants.GSC.removeGSCCourses(humanitiesCoursesList);
        return humanitiesCoursesList;
    }

    public static List<CourseInfo> getHumanitiesCoursesList() {
        FileResourceUtils fileResourceUtils = new FileResourceUtils();
        File file = fileResourceUtils.convertPathResourceToCourseListFileAtServer();
        List<RegisteredCourse> undergradCourses = parseCourseExcelFileToRegistrationCourseWithoutDuplication(file);
        List<String> humanitiesCodeList = new ArrayList<>();
        addHumanitiesCode(humanitiesCodeList);

        Set<RegisteredCourse> conditionCourse = new HashSet<>();

        for (String code : humanitiesCodeList) {
            addRegisteredCoursesByCode(undergradCourses, conditionCourse, code);
        }

        List<CourseInfo> conditionCourseList = CourseInfo.from(conditionCourse);
        HumanitiesExceptionConstants.NotHumanities.removeHumanitiesException(conditionCourseList);

        return conditionCourseList;
    }

    private static void addRegisteredCoursesByCode(List<RegisteredCourse> undergradCourses, Set<RegisteredCourse> conditionCourse, String code) {
        conditionCourse.addAll(undergradCourses.stream()
                .filter(s -> s.getCode().contains(code))
                .collect(Collectors.toSet()));
    }

    private static void addHumanitiesCode(List<String> humanitiesCodeList) {
        for (int i = 2; i <= 4; i++) {
            for (int j = 5; j <= 9; j++) {
                int num = 10 * i + j;
                humanitiesCodeList.add(("GS" + num));
            }
        }
    }

    private static List<RegisteredCourse> parseCourseExcelFileToRegistrationCourseWithoutDuplication(File file) {
        Sheet sheet = convertFileToSheet(file);
        removeDuplicate(sheet);
        return parseRegistrationCourse(sheet);
    }

    private static List<RegisteredCourse> parseRegistrationCourse(Sheet sheet) {
        List<RegisteredCourse> registeredCourses = new ArrayList<>();

        for (Row row : sheet) {
            if (!row.getCell(INDEX).getStringCellValue().matches("[0-9]+")) {
                continue;
            }
            RegisteredCourse registeredCourse = parseRowToRegisteredCourse(row);

            if (row.getCell(LIBERAL_ART_TYPE_CELL_NUMBER) != null) {
                registeredCourse.setLiberalArtType(row.getCell(LIBERAL_ART_TYPE_CELL_NUMBER).getStringCellValue());
            }
            registeredCourses.add(registeredCourse);
        }
        return registeredCourses;
    }

    private static Sheet convertFileToSheet(File file) {
        try {
            Workbook workbook = new HSSFWorkbook(new FileInputStream(file));
            Sheet sheet = workbook.getSheetAt(0);
            return sheet;
        } catch (Exception e) {
            throw new ApplicationException("강의목록 파일 분석에 오류가 발생했습니다.");
        }
    }


    private static RegisteredCourse parseRowToRegisteredCourse(Row row) {
        String indexString = row.getCell(INDEX).getStringCellValue();
        String yearString = row.getCell(YEAR_CELL_NUMBER).getStringCellValue();
        String semesterString = row.getCell(SEMESTER_CELL_NUMBER).getStringCellValue();
        String courseCode = row.getCell(CODE_CELL_NUMBER).getStringCellValue().split("-")[0].trim();
        String courseName = row.getCell(COURSE_NAME_CELL_NUMBER).getStringCellValue().trim();
        String courseType = row.getCell(COURSE_TYPE_CELL_NUMBER).getStringCellValue();
        String professor = row.getCell(PROFESSOR_NUMBER).getStringCellValue();
        String creditString = row.getCell(CREDIT_CELL_NUMBER).getStringCellValue();
        creditString = creditString.substring(creditString.length() - 1);
        String timeTable = row.getCell(TIME_TABLE_NUMBER).getStringCellValue();
        String location = row.getCell(LOCATION_NUMBER).getStringCellValue();
        String prerequisite = row.getCell(PREREQUISITE_NUMBER).getStringCellValue();

        RegisteredCourse registeredCourse = new RegisteredCourse(indexString, Integer.parseInt(yearString), semesterString,
                courseType, courseName, courseCode, Integer.parseInt(creditString),
                professor, timeTable, location, prerequisite);
        return registeredCourse;
    }

    private static void removeDuplicate(Sheet sheet) {
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
