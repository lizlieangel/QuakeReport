package com.example.android.quakereport;

public class Earthquake {
    private String magnitude;
    private String location;
    private long timeInMilliseconds;

    public Earthquake(String magnitude, String location, long time) {
        this.magnitude = magnitude;
        this.location = location;
        this.timeInMilliseconds = time;
    }

    public String getMagnitude() {
        return magnitude;
    }

    public String getLocation() {
        return location;
    }

    public long getTimeInMilliseconds() {
        return timeInMilliseconds;
    }
}
