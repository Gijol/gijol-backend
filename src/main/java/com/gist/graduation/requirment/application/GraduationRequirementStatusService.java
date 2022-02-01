package com.gist.graduation.requirment.application;

import com.gist.graduation.requirment.domain.GraduationRequirementStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GraduationRequirementStatusService {

    private final GraduationRequirementStatusRepository graduationRequirementStatusRepository;

    public void checkEnglish() {

    }

}
