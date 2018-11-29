package src.events;

import src.Database;
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
    // format for the date and time variables
    // private SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy 'at' HH:mm");
    private Date orgStartDate = new Date();
    private Date orgEndDate = new Date();

    ArrayList<Partner> partners = new ArrayList<>(); // partners for this particular event
    ArrayList<String> specs = new ArrayList<>();
    //eg: food, dj, photographer, limousine, cocaine, balloons
    // also maybe this should be just one string

    // Constructor
    public Event(int ID, String name, String serviceType, String eventType, String startDate, String endDate){
        this.ID = ID;
        this.name = name;
        this.serviceType = serviceType;
        this.eventType = eventType;
        try {
            this.orgStartDate = new SimpleDateFormat("dd.MM.yyyy 'at' HH").parse(startDate);
            this.orgEndDate = new SimpleDateFormat("dd.MM.yyyy 'at' HH").parse(endDate);
        }catch (ParseException e){
            System.out.print("Parse Exception occured: " + e.getMessage());
        }
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

    public void setOrgStartDate(String startDate){
        try {
            this.orgStartDate = new SimpleDateFormat("dd.MM.yyyy 'at' HH:mm").parse(startDate);
        } catch (ParseException e) {
            System.out.print("Parse Exception occured: " + e.getMessage());
        }
    }

    public void setOrgEndDate(String endDate){
        try {
            this.orgEndDate = new SimpleDateFormat("dd.MM.yyyy 'at' HH:mm").parse(endDate);
        } catch (ParseException e) {
            System.out.print("Parse Exception occured: " + e.getMessage());
        }
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

    //Modifiers
    public void addPartner(Partner partner){
        partners.add(partner);
    }

    public void addSpecification(String spec){
        specs.add(spec);
    }

}