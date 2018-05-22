package com.example.myapp;

public class TimeStamp {

    private Long time;
    private int state;

    public TimeStamp() {
    }

    public TimeStamp(Long time, int state) {
        this.time = time;
        this.state = state;
    }

    public Long getTime() {
        return time;
    }

    public int getState() {
        return state;
    }
}
