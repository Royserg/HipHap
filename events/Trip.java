package src.events;

import src.users.Employee;

public class Trip extends Event implements EventPricing{
    private String transport;
    private double baseTripPrice = 50000;

    //Constructor
    public Trip(int ID, String name, String serviceType, String eventType, int nbOfHoursNeeded) {
        super(ID, name, serviceType, eventType, nbOfHoursNeeded);
        this.transport = transport;
    }

    //Setters

    /**
     * set transport type
     * @param transport (String) transportation type for clients
     */
    public void setTransport(String transport) {
        this.transport = transport;
    }

    /**
     * set base trip price
     * @param price (double) - base price for the trip
     */
    public void setBaseTripPrice(double price) {this.baseTripPrice = price;}

    //getters

    /**
     * returns transport type
     * @return transport (String) - transport type for clients
     */
    public String getTransport() {
        return this.transport;
    }

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
        return (baseTripPrice + getNbOfHoursNeeded() * Employee.getHourlyWage());
    }
}