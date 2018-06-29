package com.example.myapp;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Specification user collection into mongodb.
 */
@Document(collection = "user")
public final class Account {

    private static final AtomicLong COUNTER = new AtomicLong();

    @Id
    private long id;

    @Indexed(unique = true)
    private String login;
    private String password;

    private List<TimeStamp> timestamp;

    public Account() {
    }

    public Account(final String login, final String password) {
        this.login = login;
        this.password = password;
        this.id = COUNTER.incrementAndGet();
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

    public void setLogin(final String login) {
        this.login = login;
    }

    public void setPassword(final String password) {
        this.password = password;
    }
}
