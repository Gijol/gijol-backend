package com.gist.graduation.utils;

import com.gist.graduation.exception.ApplicationException;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Component
public class FileResourceUtils {

    public static final String COURSE_LIST_FILE_PATH = "/course-information/course_information_undergraduate.xls";

    public File convertPathResourceToCourseListFileAtServer() {
        ClassPathResource undergraduateResource = new ClassPathResource(COURSE_LIST_FILE_PATH);
        try {
            InputStream inputStream = undergraduateResource.getInputStream();
            File file = File.createTempFile(UUID.randomUUID().toString(), ".xlsx");
            FileUtils.copyToFile(inputStream, file);
            return file;
        } catch (IOException e) {
            throw new ApplicationException("course list file not found");
        }
    }
}
