package src.users;

import java.util.ArrayList;

public class Partner {
    private String name;
    private String occupation;

    // Constructor
    public Partner(String name, String occupation) {
        this.name = name;
        this.occupation = occupation;
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
}
