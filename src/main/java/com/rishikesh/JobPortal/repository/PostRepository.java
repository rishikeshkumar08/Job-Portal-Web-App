package com.rishikesh.JobPortal.repository;

import com.rishikesh.JobPortal.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {

}
