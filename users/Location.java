package src.users;

import java.util.Date;
import java.util.ArrayList;

public class Location {
    String name;
    ArrayList<Date> bookedDays = new ArrayList<>();

    Location(String name) {

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


    // Set-ers

    public void setName(String name) {
        this.name = name;
    }

    public void setBookedDays(Date booking) {
        bookedDays.add(booking);
    }

    // Get-ers

    public String getName() {
        return name;
    }

    public ArrayList<Date> getBookedDays() {
        return bookedDays;
    }
}

