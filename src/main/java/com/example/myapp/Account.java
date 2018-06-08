package com.example.myapp;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Document(collection = "user")
public class Account {

    private static final AtomicLong counter = new AtomicLong();

    @Id
    private long id;


//    @Indexed(name = "login", direction = IndexDirection.DESCENDING, unique = true)
    @Indexed(unique = true)
    private String login;
    private String password;

    private List<TimeStamp> timestamp;

    public Account() {
    }

    public Account(String login, String password) {
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

    public List<TimeStamp> getTimestamp() {
        return timestamp;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
