package com.vsu.java_fish_2.dto;

import org.hibernate.validator.constraints.Length;

public class FisherDTO {
    private Long id;

    @Length(min = 6, message = "Login must be at least 6 symbols")
    private String fisherName;

    public FisherDTO(Long id, String fisherName) {
        this.id = id;
        this.fisherName = fisherName;
    }

    public FisherDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFisherName() {
        return fisherName;
    }

    public void setFisherName(String fisherName) {
        this.fisherName = fisherName;
    }

    @Override
    public String toString() {
        return "FisherDTO{" +
                "id=" + id +
                ", fisherName='" + fisherName + '\'' +
                '}';
    }
}
