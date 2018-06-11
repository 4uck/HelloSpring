package com.example.myapp;


public interface AccountCustomRepository {

    public void pushMethod(String login, TimeStamp timeStamp);

    public void unsetMethod(String login);
}
