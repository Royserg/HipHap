package src.events;

import src.users.Employee;

public class BusinessParty extends Event implements EventPricing {
    String decoration; //adding 1 attribute
    private double basePartyPrice = 30000;

    // constructor
    public BusinessParty (int ID, String name, String serviceType, String eventType, int nbOfHoursNeeded) {
        super(ID, name, serviceType, eventType, nbOfHoursNeeded);
        this.decoration = decoration;
    }

    public void setdecoration(String decoration) {
        this.decoration = decoration;
    }

    public String getDecoration() {
        return this.decoration;
    }

    @Override
    public double calculateEventPrices() {
        return (basePartyPrice + getNbOfHoursNeeded() * Employee.getHourlyWage());
    }
}
