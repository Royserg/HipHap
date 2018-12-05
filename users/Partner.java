package src.users;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Partner {
    private String name = new String();
    private String occupation;
    private int ID;
    private Location location;


    // Constructor
    public Partner(int ID, String name, String occupation, String address, String bookedDates) {
        this.name = name;
        this.occupation = occupation;
        this.location = new Location(address, bookedDates);
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
    public String getName() {
        return this.name;
    }


    public String getOccupation() {
        return this.occupation;
    }

    public String getAddress() {
        return this.location.getAddress(); // accesses address attributes from Location class
    }

    public int getID(){return this.ID;}

    public String getBookedDates() {
        ArrayList<Date> dates = this.location.getBookedDays();
        String formatedString = "";
        for (int i = 0; i < dates.size()-1; i++) {
            formatedString = formatedString + new SimpleDateFormat("dd.MM.yyyy").format(dates.get(i)) + ";";
        }
        formatedString = formatedString + new SimpleDateFormat("dd.MM.yyyy").format(dates.get(dates.size()-1));

        return formatedString;
    }

}


