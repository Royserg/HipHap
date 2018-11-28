package com.company;

public class Trip extends Event {
    String transport; // adding 1 attribute

    //Conference adds 1 constructor
    public Trip(int ID,
                String name,
                String type,
                String startDate,
                String endDate,
                String transport) {
        super(ID, name, type, startDate, endDate);
        this.transport = transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public String getTransport() {
        return this.transport;
    }
}