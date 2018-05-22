package com.example.myapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public class MyCustomRepositoryImpl implements MyCustomRepository {

    @Autowired
    protected MongoTemplate mongoTemplate;

    @Override
    public void pushMethod(String login, TimeStamp timeStamp) {
        mongoTemplate.updateFirst(
                Query.query(Criteria.where("login").is(login)),
                new Update().push("timestamp", timeStamp), User.class);
    }
}
