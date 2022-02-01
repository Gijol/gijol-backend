package com.gist.graduation.requirment.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GraduationRequirementStatusRepository extends MongoRepository<GraduationRequirementStatus, String> {
}
