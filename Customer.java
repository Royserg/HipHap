package com.company;

import java.util.ArrayList;

public class Customer {
    String name;
    // Array with the events ID that this user has
    ArrayList<Integer> ownEvents = new ArrayList<Integer>;


    // Constructor
    Customer() {

    }

    // Set-ers
    public void setName(String name) {
        this.name = name;
    }

    public void addEvent(int ID) {
        ownEvents.add(ID);
    }

    // Get-ers
    public String getName(){
        return this.name;
    }
}
