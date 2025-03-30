package com.rishikesh.JobPortal.repository;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.rishikesh.JobPortal.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import org.bson.Document;
import com.mongodb.client.AggregateIterable;
//import javax.swing.text.Document;
import java.util.ArrayList;
//import java.util.Arrays;
import java.util.List;

@Component
public class SearchRepositoryImpl implements SearchRepository{

    @Autowired
    MongoClient client;

    @Autowired
    MongoConverter converter;

    @Override
    public List<Post> findByText(String text) {
        final List<Post> posts = new ArrayList<>();

        MongoDatabase database = client.getDatabase("rishikesh");
        MongoCollection<Document> collection = database.getCollection("JobPost");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                                new Document("text",
                                new Document("query", text)
                                        .append("path", Arrays.asList("techs", "desc", "profile")))),
                                new Document("$sort",
                                new Document("exp", 1L)),
                                new Document("$limit", 5L)));

        //result is a algorithm, in this algo we want to iterate all aur List<Post> posts
        //so we will add with add(), but the return type is Post and List So we have to convert it to Post type
        //for which we use MongoConverter that has a read() that takes two parameters class type and doc type
        result.forEach(doc -> posts.add(converter.read(Post.class, doc)));
        return posts;
    }
}
