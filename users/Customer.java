package src.users;

import java.util.ArrayList;

public class Customer {
    private String name;
    // Array with the events ID that this user has
    private ArrayList<Integer> ownEvents = new ArrayList<Integer>();


    // Constructor
    public Customer(ArrayList<Integer> events, String name) {
        this.ownEvents = events;
        this.name = name;
    }

    public Customer(int eventID, String name){
        this.ownEvents.add(eventID);
        this.name = name;
    }

    // Set-ers

    /**
     * Sets name of the customer
     * @param name (String) - customer's name*/
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Adds event id to customer, connects them that way
     * @param ID (int) - event ID*/
    public void addEvent(int ID) {
        ownEvents.add(ID);
    }

    // Get-ers

    /**
     * Gets name of the customer
     * @return name (String) - customer's name*/
    public String getName(){
        return this.name;
    }

    /**
     * Gets event IDs that the customer organized with HipHap
     * @return eventIDs (ArrayList[Integer]) - event IDs*/
    public ArrayList<Integer> getOwnEvents() {
        return ownEvents;
    }
}