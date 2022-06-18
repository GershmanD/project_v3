package com.example.myapplication;

public class Student extends Person{
    private String trip_key;
    private int bus_num;

    public Student(){

    }
    public Student(String name, String id, String phoneNumber, String trip_key, int bus_num) {
        super(name, id, phoneNumber);
        this.trip_key = trip_key;
        this.bus_num = bus_num;
    }

    public String getTrip_key() {
        return trip_key;
    }

    public void setTrip_key(String trip_key) {
        this.trip_key = trip_key;
    }

    public int getBus_num() {
        return bus_num;
    }

    public void setBus_num(int bus_num) {
        this.bus_num = bus_num;
    }
}
