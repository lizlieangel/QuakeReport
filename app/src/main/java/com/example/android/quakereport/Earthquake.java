package com.example.android.quakereport;

public class Earthquake {
    private String magnitude;
    private String location;
    private long time;

    public Earthquake(String magnitude, String location, long time) {
        this.magnitude = magnitude;
        this.location = location;
        this.time = time;
    }

    public String getMagnitude() {
        return magnitude;
    }

    public String getLocation() {
        return location;
    }

    public long getTime() {
        return time;
    }
}
