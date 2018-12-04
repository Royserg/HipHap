package src.users;

import java.util.ArrayList;

public class Partner {
    private String name = new String();
    private String occupation;
    private Location location;


    // Constructor
    public Partner(String name, String occupation, String address, String bookedDates) {
        this.name = name;
        this.occupation = occupation;
        this.location = new Location(address, bookedDates);

    }

    // Set-ers
    public void setName(String name) {
        this.name = name;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }


    // Get-ers
    public String getName() {
        return this.name;
    }

    public String getOccupation() {
        return this.occupation;
    }

    public String getAddress() {
        return this.location.getAddress(); // accesses address attributes from Location class
    }
}