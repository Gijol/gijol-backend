package com.gist.graduation.course.application.description;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gist.graduation.config.exception.ApplicationException;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class DescriptionJsonParser {

    public static final String DESCRIPTION_FILE_PATH = "/course-information/description/description.json";

    public static List<DescriptionJsonDto> getCourseDescriptionFromJsonFile() {
        ClassPathResource undergraduateResource = new ClassPathResource(DESCRIPTION_FILE_PATH);
        try {
            InputStream inputStream = undergraduateResource.getInputStream();
            File file = File.createTempFile(UUID.randomUUID().toString(), ".json");
            FileUtils.copyToFile(inputStream, file);
            ObjectMapper objectMapper = new ObjectMapper();
            return Arrays.stream(objectMapper.readValue(file, DescriptionJsonDto[].class))
                    .map(s -> new DescriptionJsonDto(s.getCourseCode().trim(), s.getCourseDescription().trim()))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new ApplicationException("course json file not found: " + e.getMessage());
        }
    }
}
