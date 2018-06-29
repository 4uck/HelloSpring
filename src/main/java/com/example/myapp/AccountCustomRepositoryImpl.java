package com.example.myapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

/**
 * Implementation for {@link com.example.myapp.AccountCustomRepository}.
 */

public final class AccountCustomRepositoryImpl implements AccountCustomRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void pushMethod(final String login, final TimeStamp timeStamp) {
        mongoTemplate.updateFirst(
                Query.query(Criteria.where("login").is(login)),
                new Update().push("timestamp", timeStamp), Account.class);
    }

    @Override
    public void unsetMethod(final String login) {
        Query query = new Query();
        query.addCriteria(Criteria.where("login").is(login));

        Update update = new Update();
        update.unset("timestamp");

        // run update operation
        mongoTemplate.updateFirst(query, update, Account.class);
    }
}
