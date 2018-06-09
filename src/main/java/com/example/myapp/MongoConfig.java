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
@PropertySource("classpath:application.yml")
public class MongoConfig extends AbstractMongoConfiguration {

    @Autowired
    Environment env;

    @Override
    public MongoClient mongoClient() {

        System.out.println("//////////////////////////////");
        System.out.println(env.getProperty("mongo.host"));
        System.out.println("/////////////////////////////");

        MongoCredential mongoCredential = MongoCredential.
                createScramSha1Credential(env.getProperty("mongo.userName"),
                        env.getProperty("mongo.sourcedb"),
                        env.getProperty("mongo.password").toCharArray());

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
