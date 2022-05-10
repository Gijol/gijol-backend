package com.gist.graduation.requirment.domain.science;

import com.gist.graduation.requirment.domain.science.ScienceEnum.Status;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.gist.graduation.requirment.domain.science.ScienceEnum.Status.*;

@Getter
public class ScienceVerifier {
    public static final String EXPERIMENT = "실험";
    public ScienceBlock physicsBlock;
    public ScienceBlock chemistryBlock;
    public ScienceBlock biologyBlock;
    public List<String> recommendedCourses = new ArrayList<>();

    public ScienceVerifier(ScienceBlock physicsBlock, ScienceBlock chemistryBlock, ScienceBlock biologyBlock) {
        this.physicsBlock = physicsBlock;
        this.chemistryBlock = chemistryBlock;
        this.biologyBlock = biologyBlock;
    }

    public static ScienceVerifier of(ScienceBlock physicsBlock, ScienceBlock chemistryBlock, ScienceBlock biologyBlock) {
        return new ScienceVerifier(physicsBlock, chemistryBlock, biologyBlock);
    }

    public void checkTwoBlock(ScienceBasic scienceBasic) {
        int size = this.getCounts();
        System.out.println(size);
        if (size >= 5) return;

        switch (size) {
            case 4:
                checkFourCourses(scienceBasic);
                break;
            case 3:
                checkThreeCourses(scienceBasic);
                break;
            case 2:
                checkTwoCourses(scienceBasic);
                break;
            default:
                scienceBasic.addMessage("추천해주기에는 과목수가 너무 적어요! 본인의 관심에 맞는 과목을 수강해주세요 :)");
                break;
        }
    }

    private void checkFourCourses(ScienceBasic scienceBasic) {
        if (getStatusCount(FULL) == 2) {
            return;
        }
        if (getStatusCount(FULL) == 1) {
            List<ScienceBlock> halfStatusBlock = getHalfStatusBlock();
            findNotTakenCourseInHalfStatus(halfStatusBlock);
            scienceBasic.addMessage(String.format("%s 중 한 과목을 수강해야 합니다.", this.recommendedCourses));
        }
    }

    private void checkThreeCourses(ScienceBasic scienceBasic) {
        if (getStatusCount(FULL) == 1) {
            findNotTakenCourseInHalfStatus(getHalfStatusBlock());
            this.recommendedCourses.forEach(s -> scienceBasic.addMessage(String.format("%s를(을) 수강해야 합니다.", s)));
            return;
        }
        findNotTakenCourseInHalfStatus(getHalfStatusBlock());
        scienceBasic.addMessage(String.format("%s 중 두 과목을 수강해야 합니다.", this.recommendedCourses));

    }

    private void checkTwoCourses(ScienceBasic scienceBasic) {
        if (getStatusCount(FULL) == 1) {
            List<String> typeList = getAllBlocks().stream()
                    .filter(s -> s.getStatus().equals(EMPTY))
                    .map(s -> s.getType()).collect(Collectors.toList());
            this.recommendedCourses.addAll(typeList.stream().map(s -> String.format("(%s, %s)", s, s + EXPERIMENT)).collect(Collectors.toList()));
            scienceBasic.addMessage(String.format("%s 중 한 쌍을 수강해야 합니다.", this.recommendedCourses));
            return;
        }

        findNotTakenCourseInHalfStatus(getHalfStatusBlock());
        this.recommendedCourses.forEach(s -> scienceBasic.addMessage(String.format("%s를(을) 수강해야 합니다.", s)));
    }


    private void findNotTakenCourseInHalfStatus(List<ScienceBlock> takenCoursesHalfStatus) {
        for (ScienceBlock scienceBlock : takenCoursesHalfStatus) {
            if (scienceBlock.getUserTakenCoursesList().getTakenCourses().stream()
                    .anyMatch(s -> s.getCourseName().equals(EXPERIMENT)) && scienceBlock.getStatus().equals(HALF)) {
                this.getRecommendedCourses().add(scienceBlock.getType());
            } else {
                this.getRecommendedCourses().add(scienceBlock.getType() + EXPERIMENT);
            }
        }
    }

    private List<ScienceBlock> getHalfStatusBlock() {
        return getAllBlocks().stream()
                .filter(s -> s.getStatus().equals(HALF))
                .collect(Collectors.toList());
    }

    private Integer getCounts() {
        return physicsBlock.getSize() + chemistryBlock.getSize() + biologyBlock.getSize();
    }

    private long getStatusCount(Status status) {
        return getAllBlocks().stream()
                .map(ScienceBlock::getStatus)
                .filter(s -> s.equals(status))
                .count();
    }

    private List<ScienceBlock> getAllBlocks() {
        return List.of(physicsBlock, chemistryBlock, biologyBlock);
    }
}
