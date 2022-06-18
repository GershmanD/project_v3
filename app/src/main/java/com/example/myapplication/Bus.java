package com.example.myapplication;

public class Bus {

    private String phonenumber_driver, name_driver, trip_code;
    private int capacity_of_bus, bus_number;

    public Bus() {
    }

    public Bus(String name_driver, String phonenumber_driver,
               String trip_code, int capacity_of_bus, int bus_number) {

        this.name_driver = name_driver;
        this.phonenumber_driver = phonenumber_driver;
        this.trip_code = trip_code;
        this.capacity_of_bus = capacity_of_bus;
        this.bus_number = bus_number;
    }

    public String getTrip_code() {
        return trip_code;
    }

    public void setTrip_code(String trip_code) {
        this.trip_code = trip_code;
    }

    public int getBus_number() {
        return bus_number;
    }

    public void setBus_number(int bus_number) {
        this.bus_number = bus_number;
    }

    public String getName_driver() {
        return name_driver;
    }

    public void setName_driver(String name_driver) {
        this.name_driver = name_driver;
    }

    public String getPhonenumber_driver() {
        return phonenumber_driver;
    }

    public void setPhonenumber_driver(String phonenumber_driver) {
        this.phonenumber_driver = phonenumber_driver;
    }

    public int getCapacity_of_bus() {
        return capacity_of_bus;
    }

    public void setCapacity_of_bus(int capacity_of_bus) {
        this.capacity_of_bus = capacity_of_bus;
    }
}
