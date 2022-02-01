package com.gist.graduation.user.taken_course;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTakenCourseRepository extends MongoRepository<UserTakenCoursesList, String> {


}
