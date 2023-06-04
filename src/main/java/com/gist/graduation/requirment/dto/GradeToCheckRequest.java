package com.gist.graduation.requirment.dto;

import com.gist.graduation.requirment.domain.major.MajorType;
import com.gist.graduation.requirment.domain.minor.MinorType;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class GradeToCheckRequest {
    private MajorType majorType;
    private MultipartFile multipartFile;
}
