package src.events;

import src.users.Employee;

public class Conference extends Event implements EventPricing{
    private String officeSupplies; //adding 1 attribute
    private double baseTripPrice;

    // constructor
    public Conference(int ID, String name, String serviceType, String eventType, String startDate, String endDate, String transport) {
        super(ID, name, serviceType, eventType, startDate, endDate);
        this.officeSupplies = officeSupplies;
    }

    public void setofficeSupplies(String officeSupplies) {
        this.officeSupplies = officeSupplies;
    }

    public String getOfficeSupplies() {
        return this.officeSupplies;
    }


    @Override
    public double calculateEventPrices() {
        int nbOfHoursWorked;
        nbOfHoursWorked = (int)(getOrgStartDate().getTime() - getOrgEndDate().getTime()) /(1000 * 120); //getDate returns in millisedonds and we convert it to hours
        return (baseTripPrice + nbOfHoursWorked * Employee.getHourlyWage());
    }
}