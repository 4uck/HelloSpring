package com.example.myapp;

public class AddTimeStampRequest {

    private String login;

    private int state;

    public String getLogin() {
        return login;
    }

    public int getState() {
        return state;
    }

    public AddTimeStampRequest(String login, int state) {

        this.login = login;
        this.state = state;
    }

    public AddTimeStampRequest() {}
}
