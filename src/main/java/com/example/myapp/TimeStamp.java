package com.example.myapp;

public final class TimeStamp {

    private Long time;
    private int state;

    public TimeStamp() {
    }

    public TimeStamp(final Long time, final int state) {
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
