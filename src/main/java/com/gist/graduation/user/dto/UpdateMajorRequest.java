package com.gist.graduation.user.dto;

import com.gist.graduation.requirment.domain.major.MajorType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor()
public class UpdateMajorRequest {
    private MajorType majorType;
}
