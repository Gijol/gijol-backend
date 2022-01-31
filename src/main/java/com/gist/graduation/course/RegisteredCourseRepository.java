package com.gist.graduation.course;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisteredCourseRepository extends MongoRepository<RegisteredCourse, String> {

}
