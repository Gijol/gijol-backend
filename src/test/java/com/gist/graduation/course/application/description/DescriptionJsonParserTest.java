package com.gist.graduation.course.application.description;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DescriptionJsonParserTest {

    @Test
    void getCourseDescriptionFromJsonFile() {
        List<DescriptionJsonDto> courseDescriptionFromJsonFile = DescriptionJsonParser.getCourseDescriptionFromJsonFile();
        courseDescriptionFromJsonFile
                .forEach(System.out::println);
    }
}