package com.example.myapplication;

public class Student_As_Item extends Student{
    private boolean isSelected;

    public Student_As_Item(String name, String id, String phoneNumber,
                           String trip_key, int bus_num, boolean isSelected) {
        super(name, id, phoneNumber, trip_key, bus_num);
        this.isSelected = isSelected;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
