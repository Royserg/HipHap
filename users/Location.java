package src.users;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

public class Location {
    String address;
    ArrayList<Date> bookedDays = new ArrayList<>();

    Location(String address, String bookedDays) {
        this.address = address;

        String[] dateStrings = bookedDays.split(", ");
        for ( int i= 0; i < dateStrings.length; i++){
            Date date = null;
            try {
                date = new SimpleDateFormat("dd.MM.yyyy 'at' HH").parse(dateStrings[i]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            bookedDays.add(date);
        }
    }

    public void addDate(Date booking) {
        bookedDays.add(booking);
    }

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

    public String getAddress() {
        return address;
    }

    public ArrayList<Date> getBookedDays() {
        return bookedDays;
    }
}