package src.events;

import src.Application;
import src.Database;
import src.Helper;
import src.users.Employee;
import src.users.Partner;

import java.util.ArrayList;
import java.util.*;
import java.text.*;

public class Event {
    private String name;
    private int ID;
    private String serviceType;
    private String eventType;
    private String location;
    private int nbOfHoursNeeded;
    Employee employeeResponsible;
    private Date orgStartDate ;
    private Date orgEndDate ;

    ArrayList<Partner> partners = new ArrayList<>(); // partners for this particular event
    ArrayList<String> specs = new ArrayList<>();
    //eg: food, dj, photographer, limousine, cocaine, balloons
    // also maybe this should be just one string

    // Constructor
    public Event(int ID, String name, String serviceType, String eventType, int nbOfHoursNeeded){
        this.ID = ID;
        this.name = name;
        this.serviceType = serviceType;
        this.eventType = eventType;
        this.nbOfHoursNeeded = nbOfHoursNeeded;
        this.orgStartDate = setOrgStartDate();
        this.orgEndDate = setOrgEndDate();
    }

    // Set-ers
    public void setName(String name) {
        this.name = name;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setServiceType(String type) {
        this.serviceType = type;
    }

    public void setEventType (String type) {this.eventType = type; }

    public void setLocation(String location) {
        this.location = location;
    }

    /*TODO: think about this because it should be automatic from last employees event
    * so basically if an employee is logged in it should be looking up his last event
    * if manager is logged in he should input the ID of the employee that will be asagned the event
    * also initialises employeeResponsible attribute for an event object*/

    public Date setOrgStartDate(){
        Employee currentUser = Application.getCurrentUser();
        Date startDate;

        if( currentUser.getID() == 1111 ) {//manager
            int employeeID = Helper.getInt("Enter ID of the employee that will be asigned this event: ");
            employeeResponsible = Database.getEmployeeByID(employeeID);
            startDate = Database.getLastEventInfo(employeeResponsible);
        } else{
            employeeResponsible = currentUser;
            startDate = Database.getLastEventInfo(employeeResponsible);


            //int lastWorkingHour = startDate.getHours();

            /*+ nbOfHoursNeeded;
            int daysToMove = lastWorkingHour / 24;
            int hoursLeft = lastWorkingHour % 24;*/
        }
        return startDate;
    }


    public Date setOrgEndDate(){
        Date endDate = getOrgStartDate();
        int daysToMove = ( endDate.getHours() + nbOfHoursNeeded ) / 8; //8 working hours a day
        int hoursLeft = ( endDate.getHours() + nbOfHoursNeeded ) % 8;
        if (endDate.getDate() + daysToMove < 31 ) { //because in documentation its explained like :  If the date was April 30, for example, and the date is set to 31, then it will be treated as if it were on May 1, because April has only 30 days.
            endDate.setDate(endDate.getDate() + daysToMove);
        }else{
            endDate.setMonth(endDate.getMonth() + 1 );
            endDate.setDate(daysToMove - 31);
        }
        endDate.setHours(hoursLeft);
        return endDate;
    }

    // Get-ers
    public String getName(){
        return this.name;
    }

    public int getID() {
        return this.ID;
    }

    public String getServiceType() {
        return this.serviceType;
    }

    public String getEventType(){ return this.eventType; }

    public String getLocation() {
        return this.location;
    }

    public Date getOrgStartDate() { return this.orgStartDate; }

    public Date getOrgEndDate() { return this.orgEndDate; }

    public int getNbOfHoursNeeded() { return nbOfHoursNeeded; }

    //Modifiers
    public void addPartner(Partner partner){
        partners.add(partner);
    }

    public void addSpecification(String spec){
        specs.add(spec);
    }

}