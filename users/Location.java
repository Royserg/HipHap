package src.users;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

public class Location {
    private String address;
    private ArrayList<Date> bookedDays = new ArrayList<>();

    //constructor
    public Location(String address, String bookedDays) {
        this.address = address;

        String[] dateStrings = bookedDays.split(";");
        for ( int i= 0; i < dateStrings.length; i++){
            Date date = null;
            try {
                date = new SimpleDateFormat("dd.MM.yyyy").parse(dateStrings[i]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            this.bookedDays.add(date);
        }
    }

    /**
     * Adds date to the booked days
     * @param booking (Date) - date to add to the booked days*/
    public void addDate(Date booking) {
        bookedDays.add(booking);
    }

    /**
     * Checks if the date is available for booking
     * @param booking (Date) - date to check if it's available
     * @return isAvailable (boolean) - true if it's available, otherwise false*/
    public boolean checkDate(Date booking) {
        boolean isAvailable = true;
        for (int i = 0; i < bookedDays.size(); i++) {
            if (bookedDays.get(i).getDate() == booking.getDate()) {
                isAvailable = false;
            }
        }
        return isAvailable;
    }

    // Get-ers

    /**
     * Gets address of the location
     * @return address (String) - location address*/
    public String getAddress() {
        return address;
    }

    /**
     * Returns booked days for the location
     * @return bookedDays(ArrayList[Date]) - List of booked dates*/
    public ArrayList<Date> getBookedDays() {
        return bookedDays;
    }
}