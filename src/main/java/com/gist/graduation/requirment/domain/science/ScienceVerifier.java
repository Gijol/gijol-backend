package com.gist.graduation.requirment.domain.science;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ScienceVerifier {
    public ScienceBlock physicsBlock;
    public ScienceBlock chemistryBlock;
    public ScienceBlock biologyBlock;

    public void test() {

    }

    public ScienceVerifier(ScienceBlock physicsBlock, ScienceBlock chemistryBlock, ScienceBlock biologyBlock) {
        this.physicsBlock = physicsBlock;
        this.chemistryBlock = chemistryBlock;
        this.biologyBlock = biologyBlock;
    }

    public static ScienceVerifier of(ScienceBlock physicsBlock, ScienceBlock chemistryBlock, ScienceBlock biologyBlock){
        return new ScienceVerifier(physicsBlock, chemistryBlock, biologyBlock);
    }
}
