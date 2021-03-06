package src.events;

import src.users.Employee;

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
    public Employee employeeResponsible;
    private Date orgStartDate ;
    private Date orgEndDate ;
    private Date startOfEvent;

    ArrayList<Integer> partnersIDs = new ArrayList<>(); // partners for this particular event
    ArrayList<String> specs = new ArrayList<>(); //eg: food, dj, photographer, limousine, balloons

    /**
     * Constructor for creating a whole new event by employee*/
    public Event(int ID,  String eventType, String name, String serviceType, Date startOfEvent, Employee employeeResponsible, int nbOfHoursNeeded, String specsString){
        this.ID = ID;
        this.name = name;
        this.serviceType = serviceType;
        this.eventType = eventType;
        this.employeeResponsible = employeeResponsible;
        this.nbOfHoursNeeded = nbOfHoursNeeded;
        this.startOfEvent = startOfEvent;

        setOrgStartDate(employeeResponsible);
        setOrgEndDate();

        String[]specsHelper = specsString.split(", ");
        for(int i = 0; i < specsHelper.length; i++){
            specs.add(specsHelper[i]);
        }
    }

    /**
     * Constructor for creating a new event with reading from our database*/
    public Event(int ID, String eventType, String name, String serviceType, Employee employeeResponsible, String startDate, String endDate, String startOfEvent, int nbOfHoursNeeded, String specsString, String partnerIDs){

        this.ID = ID;
        this.eventType = eventType;
        this.name = name;
        this.serviceType = serviceType;
        this.employeeResponsible = employeeResponsible;

        try {
            this.orgStartDate = new SimpleDateFormat("dd.MM.yyyy 'at' HH").parse(startDate);
            this.orgEndDate = new SimpleDateFormat("dd.MM.yyyy 'at' HH").parse(endDate);
            this.startOfEvent = new SimpleDateFormat("dd.MM.yyyy").parse(startOfEvent);
        } catch (ParseException e) {
            System.out.print("Parse exception: " + e.getMessage());
        }

        this.nbOfHoursNeeded = nbOfHoursNeeded;

        String[] specsHelper = specsString.split(";");
        for(int i = 0; i < specsHelper.length; i++){
            specs.add(specsHelper[i]);
        }

        String[] partnerHelper = partnerIDs.split(";");
        for(int i = 0; i< partnerHelper.length; i++){
            partnersIDs.add(Integer.parseInt(partnerHelper[i]));
        }

    }

    // Setters

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
     * Setting location for the event
     * @param location (String) - location for the event*/
    public void setLocation(String location) {
        this.location = location;
    }

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
     * @param employeeResponsible (Employee) - employee responsible for organizing this event
     * */
    public void setOrgStartDate(Employee employeeResponsible){

        Date lastEmployeeEvent = employeeResponsible.getLastEventInfo();

        // keep track of today's date, keep only hours
        Date today = new Date();
        today.setMinutes(0); today.setSeconds(0);

        // set availability date for today if employee's last event is in the past
        if (lastEmployeeEvent.before(today)) {
            // if availability starts from 16:00 (end of working day), set it to next day from 8:00
            if (today.getHours() == 16 || today.getHours() > 16) {
                today.setDate(today.getDate() + 1);
                today.setHours(8);
            }
            this.orgStartDate = today;
        } else {
            if(lastEmployeeEvent.getHours() == 16 || lastEmployeeEvent.getHours() > 16) {
                lastEmployeeEvent.setDate(lastEmployeeEvent.getDate() + 1);
                lastEmployeeEvent.setHours(8);
            }
            this.orgStartDate = lastEmployeeEvent;
        }
    }

    /**
     * Setting the organizing end date, the date when the employee responsible for this event will finish the organizing*/
    private void setOrgEndDate(){
        // copy the start date
        Date endDate = new Date(getOrgStartDate().getTime());

        // working day is between 8 - 16: 8 hours per day
        int daysToMove = nbOfHoursNeeded / 8;
        int hoursLeft = nbOfHoursNeeded % 8;

        // if endDate is after 16:00 (end of working day), move date to next day
        if (endDate.getHours() + hoursLeft > 16) {
            daysToMove++;
            hoursLeft = (endDate.getHours() + hoursLeft) - 16;
            endDate.setHours(8);
        }

        if (endDate.getDate() + daysToMove < 31 ) { //because in documentation its explained like :  If the date was April 30, for example, and the date is set to 31, then it will be treated as if it were on May 1, because April has only 30 days.
            endDate.setDate(endDate.getDate() + daysToMove);
        }else{
            endDate.setMonth(endDate.getMonth() + 1 );
            endDate.setDate(endDate.getDate() + daysToMove  - 31);
        }

        endDate.setHours(endDate.getHours() + hoursLeft);


        this.orgEndDate = endDate;
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
     * Returns location of the event
     * @return location(String) - location of the event*/
    public String getLocation() {
        return this.location;
    }

    /**
     * Returns the the organizing start date, the date when the employee responsible for the event will start working on it.
     * @return startDate (Date) - the date when the responsible employee will start organizing the event*/
    public Date getOrgStartDate() { return this.orgStartDate; }

    /**
     * Returns the organizing end date, the date when the employee responsible for this event will finish the organizing
     * @return endDate (Date) - the date when the responsible employee will finish the organizing*/
    public Date getOrgEndDate() { return this.orgEndDate; }

    /**
     * Returns the number of hours needed to organize the event
     * @return nbOfHoursNeeded (int) - number of hours needed to organize the event*/
    public int getNbOfHoursNeeded() { return nbOfHoursNeeded; }

    /**
     * returns specifications of needed items like decorations, office supplies
     * @return helper(String) - items needed for the event*/
    public String getSpecs() {
        String helper = "";
        for(int i = 0; i < specs.size()-1; i++){
            helper += specs.get(i) + ",";
        }
        helper += specs.get(specs.size()-1);
        return helper;
    }

    /**
     * Returns actual start date of the event
     * @return startOfEvent (Date) - date on which the event starts*/
    public Date getStartOfEvent(){return this.startOfEvent;}

    /**
     * Returns partner IDs for the event
     * @return partnersIDs (ArrayList<Integer>) - partner IDs for the event*/
    public ArrayList<Integer> getPartnersIDs() {
        return partnersIDs;
    }

    //Modifiers

    /**
     * Adds a partener to the partner arrayList
     * @param ID (int) - partner ID for the event*/
    public void addPartner(int ID){
        partnersIDs.add(ID);
    }

    /**
     * Returns employee that is responsible for organizing this event
     * @return employeeResponsible (Employee) - employee responsible for organizing*/
    public Employee getEmployee(){
        return this.employeeResponsible;
    }

    /**
     * Saves partner IDs into a string
     * @return partnerIDs (String) - partner IDs for this event*/
    public String savePartnerIDs (){
        String helper = new String();
        for (int i = 0; i < partnersIDs.size()-1; i++){
            helper += partnersIDs.get(i) + ";";
        }
        if (partnersIDs.isEmpty() == true) {
            return "0";
        }
        helper += partnersIDs.get(partnersIDs.size()-1);
        return helper;
    }

    /**
     * Formatting string for printing event info
     * @return formattedString (String) - formatted string*/
    @Override
    public String toString(){
        String all = "";
        all += "Name: " + this.name + "| ";
        all += "Event Type: " + this.eventType + "| ";
        all += "Service Type: " + this.serviceType + "|\n";
        all += "Event Date: " + new SimpleDateFormat("dd.MM.yyyy").format(this.startOfEvent) + "|\n";
        all += "Org Start Date: " + new SimpleDateFormat("dd.MM.yyyy 'at' HH").format(this.orgStartDate) + "| ";
        all += "Org End Date: " + new SimpleDateFormat("dd.MM.yyyy 'at' HH").format(this.orgEndDate) + "|\n";

        return all;
    }
}