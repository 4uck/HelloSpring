package com.example.myapp;


import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

@Configuration
@PropertySource("classpath:database.properties")
public class MongoConfig extends AbstractMongoConfiguration {

    @Autowired
    private Environment env;

    @Override
    public MongoClient mongoClient() {

        MongoCredential mongoCredential = MongoCredential.
                createScramSha1Credential(env.getProperty("mongo.userName"), "admin", env.getProperty("mongo.password").toCharArray());

        MongoClientOptions options = MongoClientOptions.builder().build();

        ServerAddress address =
                new ServerAddress(env.getProperty("mongo.host"), Integer.parseInt(env.getProperty("mongo.port")));

        return new MongoClient(address, mongoCredential, options);
    }

    @Override
    protected String getDatabaseName() {
        return env.getProperty("mongo.db");
    }
}
