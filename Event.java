package com.company;

import java.util.ArrayList;

public class Event {
    String name;
    int ID;
    int type; /* 1-consultancy
                 2-i forgot
                 3-fully-organized*/
    String location;
    ArrayList<String> partners = new ArrayList<>; // partners for this particular event
    ArrayList<String> specs = new ArrayList<>;
    //eg: food, dj, photographer, limousine, cocaine, balloons
    // also maybe this should be just one string

    // Constructor
    Event() {

    }

    // Set-ers
    public void setName(String name) {
        this.name = name;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    // Get-ers
    public String getName(){
        return this.name;
    }

    public int getID() {
        return this.ID;
    }

    public int getType() {
        return this.getType();
    }

    public String getLocation() {
        return this.location;
    }

    // Modifiers
    public void addPartner(String partner) {
        partners.add(partner);
    }
}