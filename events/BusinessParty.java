package com.company.events;

public class BusinessParty extends Event {
    String decoration; //adding 1 attribute

    // constructor
     public BusinessParty(int ID, String name, String type, String startDate, String endDate, String decoration) {
        super(ID, name, type, startDate, endDate);
        this.decoration = decoration;
     }

    public void setdecoration(String decoration) {
        this.decoration = decoration;
    }

    public String getDecoration() {
        return this.decoration;
    }
}
