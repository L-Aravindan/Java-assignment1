package com.airtribe.learntrack.entity;

public class Person {
    protected int id;
    protected String firstName;
    protected String lastName;
    protected String email;

    public String getDisplayName() {
        return firstName + " " + lastName;
    }
}
