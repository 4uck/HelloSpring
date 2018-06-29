package com.example.myapp;

/**
 * my custom methods for interaction with mongodb collection.
 */

public interface AccountCustomRepository {

    /**
     * this method add current timestamp by login into array timestamps this user.
     * @param login user's login
     * @param timeStamp current time in milliseconds, and state for this time.
     */
    public void pushMethod(String login, TimeStamp timeStamp);

    /**
     * this method delete all timestamps for this user.
     * @param login users's login
     */
    public void unsetMethod(String login);
}
