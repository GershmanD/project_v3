package com.example.myapplication;

public class Trip {
    int but_amount;
    String title;
    String key;
    String date;
    String place;

    public Trip() {
    }

    public Trip(String title, String key, String date, String place) {
        this.title = title;
        this.key = key;
        this.date = date;
        this.place = place;
    }
    public Trip(int but_amount, String title, String key, String date, String place) {
        this.but_amount = but_amount;
        this.title = title;
        this.key = key;
        this.date = date;
        this.place = place;
    }

    public Trip(String key, String date) {
        this.key = key;
        this.date = date;
    }

    public int getBut_amount() {
        return but_amount;
    }

    public void setBut_amount(int but_amount) {
        this.but_amount = but_amount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
