package src.users;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Employee {
    private ArrayList<Integer> eventIDs;
    private int ID;
    private String name;
    private String password;
    private String email;
    private static double hourlyWage = 200;
    private Date availabilityDate;


    // Constructor
    public Employee(int ID, ArrayList<Integer> ids, String name, String pass, String email, String avDate) {
        this.ID = ID;
        this.eventIDs = new ArrayList<>(ids);
        this.name = name;
        this.password = pass;
        this.email = email;
        try {
            this.availabilityDate = new SimpleDateFormat("dd.MM.yyyy 'at' HH").parse(avDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    //Setters

    /**
     * set hourlyWage for an employee
     * @param hourlyWage (double) of employee
     */
    public void setHourlyWage(double hourlyWage){
        this.hourlyWage = hourlyWage;
    }

    // Getters

    /**
     * get Employee id
     * @return id (int) of Employee
     */
    public int getID() {
        return ID;
    }

    /**
     * get Employee Event IDs
     * @return eventIDs (ArrayList<Integer>) of Employee
     */
    public ArrayList<Integer> getEventIDs() {
        return eventIDs;
    }


    /**
     * get Employee name
     * @return name (String) of Employee
     */
    public String getName() {
        return name;
    }

    /**
     * get Employee password
     * @return password (String) of Employee
     */
    public String getPassword() {
        return password;
    }

    /**
     * get Employee email address
     * @return email (String) of Employee
     */
    public String getEmail() {
        return email;
    }

    /**
     * get hourly wage for the employee
     * @return hourlyWage (double) of employee
     */
    public static double getHourlyWage() { return hourlyWage; }

    public int getLastEventID() {
        return eventIDs.get(eventIDs.size()-1);
    }

    public Date getLastEventInfo(){
        return availabilityDate;
    }

    //modifiers

    public void addEvent(int ID){
        eventIDs.add(ID);
    }

    public void removeEventID(int eventID) {
        for (int i = 0; i < eventIDs.size(); i++)
            if (eventIDs.get(i) == eventID) {
                eventIDs.remove(i);
                break;
            }
    }

}

