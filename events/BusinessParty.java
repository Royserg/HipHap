package src.events;

import src.users.Employee;

import java.util.Date;

public class BusinessParty extends Event implements EventPricing {
    private double basePartyPrice = 10000;
    private double totalPartyPrice;

    /**
     * Constructor for creating a whole new event object by employee.
     * @param ID (int) id of the object
     * @param eventType (String) type of the event represented by String
     * @param name (String) name of the event
     * @param serviceType (String) type of the provided service for this event (consultancy, full-organisation, planning)
     * @param startOfEvent (Date) start date of the event
     * @param employeeResponsible (Employee) assigned employee who is working on that event
     * @param nbOfHoursNeeded (int) number of hours needed in order to complete the preparation for event
     * @param specs (String) extra specification for the event
     */
    public BusinessParty(int ID, String eventType, String name, String serviceType, Date startOfEvent, Employee employeeResponsible, int nbOfHoursNeeded, String specs) {
        super(ID, eventType, name, serviceType, startOfEvent, employeeResponsible, nbOfHoursNeeded, specs);
    }

    /**
     * Constructor for creating a new event with reading from our database.
     * @param ID (int) id of the object
     * @param eventType (String) type of the event represented by String
     * @param name (String) name of the event
     * @param serviceType (String) type of the provided service for this event (consultancy, full-organisation, planning)
     * @param employee (Employee) assigned employee who is working on that event
     * @param startDate (Date) start date working on the event
     * @param endDate (Date) last date of working on the event
     * @param startOfEvent (Date) start date of the event
     * @param nbOfHoursNeeded (int) number of hours needed in order to complete the preparation for event
     * @param specs (String) extra specification for the event
     * @param partnerIDs (String) line of semicolon separated ids of Partners required for the event
     */
    public BusinessParty(int ID, String eventType, String name, String serviceType, Employee employee, String startDate, String endDate, String startOfEvent, int nbOfHoursNeeded, String specs, String partnerIDs){
        super(ID, eventType, name, serviceType, employee, startDate, endDate, startOfEvent, nbOfHoursNeeded, specs, partnerIDs);

    }

    //Setters

    /**
     * Sets base party price.
     * @param price (double) - base price for the party*/
    public void setBasePartyPrice(double price) {this.basePartyPrice = price;}

    //getters

    /**
     * Returns base party price.
     * @return basePartyPrice (double) - base price for the party*/
    public double getBaseTripPrice() { return this.basePartyPrice; }

    /**
     * Returns total party that customer should pay.
     * @return totalPartyPrice (double)  - price that customer should pay for the event*/
    public double getTotalPartyPrice(){ return this.totalPartyPrice; }

    /**
     * Calculates total party price based on base price, number of hours needed to organize, hourly wage and number of additional specifications*/
    @Override
    public void calculateEventPrices() {
        int nbOfSpecs = super.specs.size();
        totalPartyPrice =  (basePartyPrice + getNbOfHoursNeeded() * Employee.getHourlyWage() + nbOfSpecs * 400);
    }
}