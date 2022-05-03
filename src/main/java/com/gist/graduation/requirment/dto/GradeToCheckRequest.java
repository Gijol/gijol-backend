package com.gist.graduation.requirment.dto;

import com.gist.graduation.requirment.domain.MajorType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GradeToCheckRequest {
    private MultipartFile multipartFile;
    private MajorType majorType;
}
