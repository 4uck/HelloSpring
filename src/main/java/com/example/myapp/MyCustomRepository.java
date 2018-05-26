package com.example.myapp;

import java.util.List;

public interface MyCustomRepository {

    public void pushMethod(String login, TimeStamp timeStamp);

    public void unsetMethod(String login);
}
