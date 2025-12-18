package com.vsu.java_fish_2.Entity;

public class Catch {
    private long id;
    private String fishName;
    private float weight;
    private float length;
    private long fisherId;

    public Catch() {
    }

    public Catch(String fishName, float weight, float length) {
        this.fishName = fishName;
        this.weight = weight;
        this.length = length;
    }

    public Catch(long id, String fishName, float weight, float length, long fisherId) {
        this.id = id;
        this.fishName = fishName;
        this.weight = weight;
        this.length = length;
        this.fisherId = fisherId;
    }

    public Catch(long id, String fishName, float weight, float length) {
        this.id = id;
        this.fishName = fishName;
        this.weight = weight;
        this.length = length;
    }

    public Catch(String fishName, float weight, float length, long fisherId) {
        this.fishName = fishName;
        this.weight = weight;
        this.length = length;
        this.fisherId = fisherId;
    }

    public long getFisherId() {
        return fisherId;
    }

    public void setFisherId(long fisherId) {
        this.fisherId = fisherId;
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
}