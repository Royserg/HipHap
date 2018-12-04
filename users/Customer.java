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

    public ArrayList<Integer> getOwnEvents() {
        return ownEvents;
    }
}
