package com.gist.graduation.requirment.dto;

import com.gist.graduation.requirment.domain.major.MajorType;
import com.gist.graduation.requirment.domain.minor.MinorType;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class DemoGradeToCheckRequest {
    private MajorType majorType;
    private MinorType minorType;
    private MultipartFile multipartFile;
}
