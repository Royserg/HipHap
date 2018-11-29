package src.events;

import src.users.Employee;

public class Conference extends Event implements EventPricing{
    private String officeSupplies; //adding 1 attribute
    private static double baseConferencePrice = 10000 ;

    // constructor
    public Conference(int ID, String name, String serviceType, String eventType, int nbOfHoursNeeded) {
        super(ID, name, serviceType, eventType, nbOfHoursNeeded);
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
        return (baseConferencePrice + getNbOfHoursNeeded() * Employee.getHourlyWage());
    }
}