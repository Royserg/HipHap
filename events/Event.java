package src.events;

import src.Application;
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
    private int nbOfHoursNeeded;
    Employee employeeResponsible;
    private Date orgStartDate;
    private Date orgEndDate;

    ArrayList<Partner> partners = new ArrayList<>(); // partners for this particular event
    ArrayList<String> specs = new ArrayList<>();
    //eg: food, dj, photographer, limousine, balloons
    // also maybe this should be just one string

    /**
     * Constructor for creating a whole new event by employee
    * */
    public Event(int ID,  String eventType, String name, String serviceType, Employee employeeResponsible, int nbOfHoursNeeded, String specsString){
        this.ID = ID;
        this.name = name;
        this.serviceType = serviceType;
        this.eventType = eventType;
        this.employeeResponsible = employeeResponsible;
        this.nbOfHoursNeeded = nbOfHoursNeeded;
        this.orgStartDate = setOrgStartDate(employeeResponsible);
        this.orgEndDate = setOrgEndDate();

        String[]specsHelper = specsString.split(", ");
        for(int i = 0; i < specsHelper.length; i++){
            specs.add(specsHelper[i]);
        }

    }

    /**
     * Constructor for creating a new event with reading from our database
     * */
    public Event(int ID, String eventType, String name, String serviceType, String startDate, String endDate, int nbOfHoursNeeded, String specsString){
        this.ID = ID;
        this.eventType = eventType;
        this.name = name;
        this.serviceType = serviceType;

        try {
            this.orgStartDate = new SimpleDateFormat("dd.MM.yyyy 'at' HH").parse(startDate);
            this.orgEndDate = new SimpleDateFormat("dd.MM.yyyy 'at' HH").parse(endDate);
        } catch (ParseException e) {
            System.out.print("Parse exception: " + e.getMessage());
        }

        this.nbOfHoursNeeded = nbOfHoursNeeded;

        String[]specsHelper = specsString.split(", ");
        for(int i = 0; i < specsHelper.length; i++){
            specs.add(specsHelper[i]);
        }

    }

    // Set-ers

    /**
     * Setting event name
     * @param name (String) - event name*/
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setting event ID
     * @param ID (int) - event ID*/
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Setting type of service
     * @param type (String) - type of service : consultation, planning, full organization*/
    public void setServiceType(String type) {
        this.serviceType = type;
    }

    /**
     * Setting event type
     * @param type (String) - type of event: Trip, Business party, Conference*/
    public void setEventType (String type) {this.eventType = type; }


    /**
     * sets specification for the event, items needed like office supplies
     * @param  specsString (String) - items needed for the event*/
    public void setSpecs(String specsString) {
        String[]specsHelper = specsString.split(", ");
        for(int i = 0; i < specsHelper.length; i++){
            specs.add(specsHelper[i]);
        }
    }

    /**
     * Setting the organizing start date, the date when the employee responsible for the event will start working on it.
     * Date is in format "dd.MM.yyyy 'at' HH"
     * @param employeeResponsible (Employee) - employee responsible for organizing this event
     * @return startDate (Date) - the date when the responsible employee will start organizing the event*/

    public Date setOrgStartDate( Employee employeeResponsible){
        Employee currentUser = Application.getCurrentUser();
        Date startDate;

        if( currentUser.getID() == 1111 ) {//manager
            int employeeID = Helper.getInt("Enter ID of the employee that will be asigned this event: ");
            startDate = employeeResponsible.getLastEventInfo();
        } else{
            startDate = employeeResponsible.getLastEventInfo();
        }
        return startDate;
    }

    /**
     * Setting the organizing end date, the date when the employee responsible for this event will finish the organizing
     * Date is in the format "dd.MM.yyyy 'at' HH"
     * @return endDate (Date) - the date when the responsible employee will finish the organizing*/
    public Date setOrgEndDate(){
        Date endDate = getOrgStartDate();
        int daysToMove = ( endDate.getHours() + nbOfHoursNeeded ) / 8; //8 working hours a day
        int hoursLeft = ( endDate.getHours() + nbOfHoursNeeded ) % 8;
        if (endDate.getDate() + daysToMove < 31 ) { //because in documentation its explained like :  If the date was April 30, for example, and the date is set to 31, then it will be treated as if it were on May 1, because April has only 30 days.
            endDate.setDate(endDate.getDate() + daysToMove);
        }else{
            endDate.setMonth(endDate.getMonth() + 1 );
            endDate.setDate(endDate.getDate() + daysToMove  - 31);
        }
        endDate.setHours(hoursLeft);
        return endDate;
    }

    // Get-ers

    /**
    * Returns name of the event
    * @return name (String) - name of the event*/
    public String getName(){
        return this.name;
    }

    /**
     * Returns event ID
     * @return ID (int) - ID of the event*/
    public int getID() {
        return this.ID;
    }

    /**
     * Returns service type of the event
     * @return serviceType (string) - type of service : consultation, planning, full organization*/
    public String getServiceType() {
        return this.serviceType;
    }

    /**
     * Returns event type of the event
     * @return eventType (String) - type of event: Trip, Business party, Conference*/
    public String getEventType(){ return this.eventType; }


    /**
     * Returns the the organizing start date, the date when the employee responsible for the event will start working on it.
     * Date is in format "dd.MM.yyyy 'at' HH"
     * @return startDate (Date) - the date when the responsible employee will start organizing the event*/
    public Date getOrgStartDate() { return this.orgStartDate; }

    /**
     * Returns the organizing end date, the date when the employee responsible for this event will finish the organizing
     * Date is in the format "dd.MM.yyyy 'at' HH"
     * @return endDate (Date) - the date when the responsible employee will finish the organizing*/
    public Date getOrgEndDate() { return this.orgEndDate; }

    /**
     * Returns the number of hours needed to organize the event
     * @return nbOfHoursNeeded (int) - number of hours needed to organize the event*/
    public int getNbOfHoursNeeded() { return nbOfHoursNeeded; }

    /**
     * returns specifications of needed items like decorations, office supplies
     * @return specs (arrayList <String>) - items needed for the event*/
    public ArrayList<String> getSpecs() {
        return specs;
    }

    //Modifiers

    /**
     * Adds a partener to the partner arrayList
     * @param partner (Partner) - partner for the event*/
    public void addPartner(Partner partner){
        partners.add(partner);
    }

    /**
     * Adds a specification of the event to the specification arrayList
     * @param spec (String) - specification for the event*/
    public void addSpecification(String spec){
        specs.add(spec);
    }

}