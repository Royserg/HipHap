package com.company;

import java.util.ArrayList;

public class Partner {
    String name;
    String occupation;

    // Constructor
    Partner() {

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
