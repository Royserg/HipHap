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
    /**
     * Sets partner name
     * @param name (String) - partner's name*/
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets occupation of the partner - exmpl. catering, hotel, bar...
     * @param occupation (String) - partners occupation*/
    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }


    // Get-ers
    /**
     * Gets partner name
     * @return name (String) - partner name*/
    public String getName() {
        return this.name;
    }

    /**
     * Gets partner's occupation
     * @return occupation (String) - partner's occupation*/
    public String getOccupation() {
        return this.occupation;
    }

    /**
     * Gets address of partners location
     * @return address (String) - partner's address*/
    public String getAddress() {
        return this.location.getAddress(); // accesses address attributes from Location class
    }

    /**
     * Gets partner's IDs
     * @return ID (int) - partner's ID*/
    public int getID(){return this.ID;}

    /**
     * Gets booked dates of the partner as a formatted string
     * @return formattedString (String) - booked dates as a formatted string*/
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