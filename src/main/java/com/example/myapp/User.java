package com.example.myapp;

import java.util.concurrent.atomic.AtomicLong;

public class User {

    private static final AtomicLong counter = new AtomicLong();



//    @Indexed(name = "login", direction = IndexDirection.DESCENDING, unique = true)
    private String login;
    private String password;

    public User() {
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
