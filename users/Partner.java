package src.users;

import java.util.ArrayList;

public class Partner {
    private String name;
    private String occupation;
    private int ID;

    // Constructor
    public Partner(String name, String occupation, int ID) {
        this.name = name;
        this.occupation = occupation;
        this.ID = ID;
    }

    // Set-ers
    public void setName(String name) {
        this.name = name;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    // Get-ers
    public String getName(){ return this.name;}

    public String getOccupation(){return this.occupation;}

    public int getID(){return this.ID;}
}
