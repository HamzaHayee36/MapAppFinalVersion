package com.example.mapapp;

public class DataModel {

    // string variables for our name and job
    private String email;
    private String roll_number;
    private String password;
    private String name;


    public DataModel(String name, String email, String roll_number, String password) {
        this.name = name;
        this.email = email;
        this.roll_number = roll_number;
        this.password = password;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRollnumber() {
        return roll_number;
    }

    public void setRollnumber(String rollnumber) {
        this.roll_number = rollnumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

