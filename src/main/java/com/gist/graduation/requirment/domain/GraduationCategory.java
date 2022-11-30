package com.gist.graduation.requirment.domain;

import com.gist.graduation.requirment.domain.etc.EtcMandatory;
import com.gist.graduation.requirment.domain.humanities.Humanities;
import com.gist.graduation.requirment.domain.language.LanguageBasic;
import com.gist.graduation.requirment.domain.major.Major;
import com.gist.graduation.requirment.domain.minor.Minor;
import com.gist.graduation.requirment.domain.other.OtherUncheckedClass;
import com.gist.graduation.requirment.domain.science.ScienceBasic;
import lombok.Getter;

import static com.gist.graduation.requirment.domain.GraduationRequirementStatus.TOTAL_CREDIT_CONDITION;


@Getter
public class GraduationCategory {
    private final LanguageBasic languageBasic;
    private final ScienceBasic scienceBasic;
    private final Major major;
    private final Humanities humanities;
    private final EtcMandatory etcMandatory;
    private final OtherUncheckedClass otherUncheckedClass;
    private final Minor minor;

    public GraduationCategory() {
        this.languageBasic = new LanguageBasic();
        this.scienceBasic = new ScienceBasic();
        this.major = new Major();
        this.humanities = new Humanities();
        this.etcMandatory = new EtcMandatory();
        this.otherUncheckedClass = new OtherUncheckedClass();
        this.minor = new Minor();
    }

    public boolean checkSatisfied(GraduationRequirementStatus graduationRequirementStatus) {
        Integer totalCredits = graduationRequirementStatus.getTotalCredits();

        if (totalCredits < TOTAL_CREDIT_CONDITION) {
            otherUncheckedClass.addMessage(String.format("전체 학점이 %d학점을 넘어야 합니다.", TOTAL_CREDIT_CONDITION));
        }

        return languageBasic.getSatisfied() && scienceBasic.getSatisfied() && major.getSatisfied() && humanities.getSatisfied()
                && etcMandatory.getSatisfied() && totalCredits >= TOTAL_CREDIT_CONDITION;

    }
}
