package com.example.myapp;

import java.util.concurrent.atomic.AtomicLong;

public class Customer {

    private static final AtomicLong counter = new AtomicLong();

    private String firstName;
    private String lastName;

    public Customer() {}

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }


}
