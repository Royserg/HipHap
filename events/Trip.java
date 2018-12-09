package src.events;

import src.users.Employee;

import java.util.Date;

public class Trip extends Event implements EventPricing{
    private double baseTripPrice = 20000;
    private double totalTripPrice;


    /**
     * Constructor for creating a whole new trip object by employee
     * @param ID (int) id of the object
     * @param eventType (String) type of the event represented by String
     * @param name (String) name of the event
     * @param serviceType (String) type of the provided service for this event (consultancy, full-organisation, planning)
     * @param startOfEvent (Date) start date of the event
     * @param employeeResponsible (Employee) assigned employee who is working on that event
     * @param nbOfHoursNeeded (int) number of hours needed in order to complete the preparation for event
     * @param specs (String) extra specification for the event
     */
    public Trip(int ID, String eventType, String name, String serviceType, Date startOfEvent, Employee employeeResponsible, int nbOfHoursNeeded, String specs) {
        super(ID, eventType, name, serviceType, startOfEvent, employeeResponsible, nbOfHoursNeeded, specs);
    }

    /**
     * Constructor for creating a new trip with reading from our database
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
     * */
    public Trip(int ID, String eventType, String name, String serviceType, Employee employee, String startDate, String endDate, String startOfEvent, int nbOfHoursNeeded, String specs, String partnerIDs){
        super(ID, eventType, name, serviceType, employee, startDate, endDate, startOfEvent, nbOfHoursNeeded, specs, partnerIDs);
    }

    //Setters

    /**
     * set base trip price
     * @param price (double) - base price for the trip*/
    public void setBaseTripPrice(double price) {this.baseTripPrice = price;}

    //getters

    /**
     * Returns base trip price
     * @return baseTripPrice (double) - base price for the trip*/
    public double getBaseTripPrice() { return this.baseTripPrice; }

    /**
     * Returns total trip price that a customer should pay for the trip
     * @return totalTripPrice (double) - total price for the trip*/
    public double getTotalTripPrice() {return this.totalTripPrice; }

    /**
     * Calculates total trip price based on base price, number of hours needed to organize, hourly wage and number of additional specifications*/
    @Override
    public void calculateEventPrices() {
        int nbOfSpecs = super.specs.size();
        totalTripPrice = (baseTripPrice + getNbOfHoursNeeded() * Employee.getHourlyWage() + nbOfSpecs * 400);
    }
}