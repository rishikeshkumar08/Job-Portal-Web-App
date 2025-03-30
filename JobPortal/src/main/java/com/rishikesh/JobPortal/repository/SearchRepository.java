package com.rishikesh.JobPortal.repository;

import com.rishikesh.JobPortal.model.Post;

import java.util.List;

public interface SearchRepository {

    List<Post> findByText(String text);
}
