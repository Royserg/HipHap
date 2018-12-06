package src.events;

import src.users.Employee;

import java.util.Date;

public class BusinessParty extends Event implements EventPricing {
    private double basePartyPrice = 10000;
    private double totalPartyPrice;

    /**
     * Constructor for creating a whole new event by employee
     * */
    public BusinessParty(int ID, String eventType, String name, String serviceType, Date startOfEvent, Employee employeeResponsible, int nbOfHoursNeeded, String specs) {
        super(ID, eventType, name, serviceType, startOfEvent, employeeResponsible, nbOfHoursNeeded, specs);
    }

    /**
     * Constructor for creating a new event with reading from our database
     * */
/*
    public BusinessParty(int ID, String eventType, String name, String serviceType, Employee employee, String startDate, String endDate, int nbOfHoursNeeded, String specs){
        super(ID, eventType, name, serviceType, employee, startDate, endDate, nbOfHoursNeeded, specs);
*/
    public BusinessParty(int ID, String eventType, String name, String serviceType, Employee employee, String startDate, String endDate, String startOfEvent, int nbOfHoursNeeded, String specs, String partnerIDs){
        super(ID, eventType, name, serviceType, employee, startDate, endDate, startOfEvent, nbOfHoursNeeded, specs, partnerIDs);

    }

    //Setters

    /**
     * set base party price
     * @param price (double) - base price for the party
     */
    public void setBaseTripPrice(double price) {this.basePartyPrice = price;}

    //getters

    /**
     * returns base party price
     * @return basePartyPrice (double) - base price for the party
     */
    public double getBaseTripPrice() { return this.basePartyPrice; }

    public double getTotalPartyPrice(){ return this.totalPartyPrice; }

    /**
     * Calculates the event price that the customer needs to pay in order for the event to be organized*/
    @Override
    public void calculateEventPrices() {
        int nbOfSpecs = super.specs.size();
        totalPartyPrice =  (basePartyPrice + getNbOfHoursNeeded() * Employee.getHourlyWage() + nbOfSpecs * 400);
    }
}
