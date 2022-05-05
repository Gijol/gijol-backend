package com.gist.graduation.requirment.dto;

import com.gist.graduation.requirment.domain.MajorType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
public class GradeToCheckRequest {
    private MajorType majorType;
    private MultipartFile multipartFile;
}
