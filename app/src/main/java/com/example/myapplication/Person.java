package com.example.myapplication;

public class Person {
    private String name;
    private String key;
    private String phoneNumber;


    public Person() {
    }
    public Person(String name, String key,String phoneNumber) {
        this.name = name;
        this.key = key;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public void Teacher (String name, String phoneNumber, String id){
        int type = 1;
    }
    public void Student (String name, String phoneNumber, String name_of_teacher){

    }


}
