package com.vsu.java_fish_2.request;

import org.hibernate.validator.constraints.Length;

public class CreateFisherRequest {
    @Length(min = 6, message = "login must be not less than 6 symbols")
    private String fisherName;
    private String password;

    public CreateFisherRequest() {
    }

    public CreateFisherRequest(String fisherName) {
        this.fisherName = fisherName;
    }

    public CreateFisherRequest(String fisherName, String password) {
        this.fisherName = fisherName;
        this.password = password;
    }

    public String getFisherName() {
        return fisherName;
    }

    public void setFisherName(String fisherName) {
        this.fisherName = fisherName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ProfileDTO{" +
                ", name='" + fisherName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
