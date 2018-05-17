package com.example.myapp;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.concurrent.atomic.AtomicLong;

@Document(collection = "user")
public class User {

    private static final AtomicLong counter = new AtomicLong();

    @Id
    private long id;


//    @Indexed(name = "login", direction = IndexDirection.DESCENDING, unique = true)
    @Indexed(unique = true)
    private String login;
    private String password;

    public User() {
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        this.id = counter.incrementAndGet();
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
