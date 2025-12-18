package com.vsu.java_fish_2.dto;

public class CatchDTO {
    private long id;
    private String fishName;
    private float weight;
    private float length;

    public CatchDTO() {
    }

    public CatchDTO(long id, String fishName, float weight, float length) {
        this.id = id;
        this.fishName = fishName;
        this.weight = weight;
        this.length = length;
    }

    public CatchDTO(String fishName, float weight, float length) {
        this.fishName = fishName;
        this.weight = weight;
        this.length = length;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFishName() {
        return fishName;
    }

    public void setFishName(String fishName) {
        this.fishName = fishName;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "CatchDTO{" +
                "id=" + id +
                ", fishName='" + fishName + '\'' +
                ", weight=" + weight +
                ", length=" + length +
                '}';
    }
}
