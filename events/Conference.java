package src.events;

import src.users.Employee;

public class Conference extends Event implements EventPricing{
    private String officeSupplies; //adding 1 attribute
    private static double baseConferencePrice = 10000 ;

    /**
     * Constructor for creating a whole new event by employee
     * */
    public Conference(int ID,  String eventType, String name, String serviceType, Employee employeeResponsible, int nbOfHoursNeeded, String officeSupplies) {
        super(ID, eventType, name, serviceType, employeeResponsible, nbOfHoursNeeded);
        this.officeSupplies = officeSupplies;
    }

    /**
     * Constructor for creating a new event with reading from our database
     * */
    public Conference(int ID, String eventType, String name, String serviceType, String startDate, String endDate, int nbOfHoursNeeded, String officeSupplies){
        super(ID, eventType, name, serviceType, startDate, endDate, nbOfHoursNeeded);
        this.officeSupplies = officeSupplies;
    }

    /**
     * Setting office supplies needed for the conference
     * @param officeSupplies (String) - office supplies needed for the conference*/
    public void setofficeSupplies(String officeSupplies) {
        this.officeSupplies = officeSupplies;
    }

    /**
     * Getting office supplies needed for the conference
     * @return officeSupplies (String) - office supplies needed for the conference*/
    public String getOfficeSupplies() {
        return this.officeSupplies;
    }


    @Override
    public double calculateEventPrices() {
        return (baseConferencePrice + getNbOfHoursNeeded() * Employee.getHourlyWage());
    }
}