package src.events;

import src.users.Employee;

public class BusinessParty extends Event implements EventPricing {
    String decoration; //adding 1 attribute
    private double baseTripPrice;

    // constructor
    public BusinessParty (int ID, String name, String serviceType, String eventType, String startDate, String endDate, String transport) {
        super(ID, name, serviceType, eventType, startDate, endDate);
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
        int nbOfHoursWorked;
        nbOfHoursWorked = (int)(getOrgStartDate().getTime() - getOrgEndDate().getTime()) /(1000 * 120); //getDate returns in millisedonds and we convert it to hours
        return (baseTripPrice + nbOfHoursWorked * Employee.getHourlyWage());
    }
}
