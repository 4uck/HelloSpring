package com.example.myapp;

import java.util.UUID;

public class Token {

    private UUID access_token;

    public Token() {
        this.access_token = UUID.randomUUID();
    }

    public UUID getAccess_token() {
        return access_token;
    }
}
