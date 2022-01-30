package com.gist.graduation.course.from2018;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConditionCourseRepository extends MongoRepository<ConditionCourse, String> {

}
