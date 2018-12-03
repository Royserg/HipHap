package src.events;

import src.users.Employee;

public class Trip extends Event implements EventPricing{
    private double baseTripPrice = 50000;

    /**
     * Constructor for creating a whole new event by employee
     * */
    public Trip(int ID,  String eventType, String name, String serviceType, Employee employeeResponsible, int nbOfHoursNeeded, String specs) {
        super(ID, eventType, name, serviceType, employeeResponsible, nbOfHoursNeeded, specs);
    }

    /**
     * Constructor for creating a new event with reading from our database
     * */
    public Trip(int ID, String eventType, String name, String serviceType, String startDate, String endDate, int nbOfHoursNeeded, String specs){
        super(ID, eventType, name, serviceType, startDate, endDate, nbOfHoursNeeded, specs);
    }

    //Setters

    /**
     * set base trip price
     * @param price (double) - base price for the trip
     */
    public void setBaseTripPrice(double price) {this.baseTripPrice = price;}

    //getters

    /**
     * returns base trip price
     * @return baseTripPrice (double) - base price for the trip
     */
    public double getBaseTripPrice() { return this.baseTripPrice; }

    /**
     * calculates price of the event that clients need to pay
     * @return finalPrice (double) - price that clients need to pay for organization*/
    @Override
    public double calculateEventPrices() {
        int nbOfSpecs = super.specs.size();
        return (baseTripPrice + getNbOfHoursNeeded() * Employee.getHourlyWage() + nbOfSpecs * 400);
    }
}