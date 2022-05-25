package com.gist.graduation.requirment.domain.science;

import com.gist.graduation.requirment.domain.science.ScienceEnum.Status;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.gist.graduation.requirment.domain.constants.ScienceBasicConstants.Science.SOFTWARE_BASIC_AND_CODING;
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

    public void checkThreeBlock(ScienceBasic scienceBasic) {
        this.recommendedCourses.clear();
        findNotTakenCourseInHalfStatus(getHalfStatusBlock());
        List<String> typeList = getAllBlocks().stream()
                .filter(s -> s.getStatus().equals(EMPTY))
                .map(s -> s.getType()).collect(Collectors.toList());
        this.recommendedCourses.addAll(typeList);
        this.recommendedCourses.addAll(typeList.stream().map(s -> s + EXPERIMENT).collect(Collectors.toList()));

        if(!this.recommendedCourses.isEmpty()){
            scienceBasic.addMessage("컴퓨터 프로그래밍 과목을 들으실 계획이 아닌 경우에는 다음과 같이 수강해야 합니다.");
            this.recommendedCourses.forEach(s -> scienceBasic.addMessage(String.format("%s를(을) 수강해야 합니다.", s)));
        }

        checkSoftwareBasic(scienceBasic);
    }

    public void checkSoftwareBasic(ScienceBasic scienceBasic) {
        if (!scienceBasic.getUserTakenCoursesList().contains(SOFTWARE_BASIC_AND_CODING)) {
            scienceBasic.addMessage(String.format("%s를(을) 수강해야 합니다.", SOFTWARE_BASIC_AND_CODING));
        }
    }

    public void checkTwoBlock(ScienceBasic scienceBasic, boolean tookComputer) {


        int size = this.getCounts();
        if (size >= 5) return;

        if (!tookComputer){
            scienceBasic.addMessage("컴퓨터 프로그래밍 과목을 들으실 계획인 경우에는 다음과 같이 수강해야 합니다.(이 경우는 들어야 하는 최소학점이 17학점 입니다.)");
            scienceBasic.addMessage("컴퓨터 프로그래밍과목을 수강해야 합니다.");
        }
        if (size == 4) {
            checkFourCourses(scienceBasic);
        }
        else if(size==3) {
            checkThreeCourses(scienceBasic);
        }
        else if(size==2) {
            checkTwoCourses(scienceBasic);
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
                    .anyMatch(s -> s.getCourseName().contains(EXPERIMENT)) && scienceBlock.getStatus().equals(HALF)) {
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

    public int getCounts() {
        int count = physicsBlock.getSize() + chemistryBlock.getSize() + biologyBlock.getSize();
        if (biologyBlock.getUserTakenCoursesList().getTakenCourses().stream().filter(s -> !s.getCourseName().contains("실험")).count() > 1) {
            count--;
        }
        return count;
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
