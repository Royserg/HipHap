package src.events;

import src.users.Employee;

public class BusinessParty extends Event implements EventPricing {
    String decoration; //adding 1 attribute
    private double basePartyPrice = 30000;

    /**
     * Constructor for creating a whole new event by employee
     * */
    public BusinessParty(int ID,  String eventType, String name, String serviceType, Employee employeeResponsible, int nbOfHoursNeeded, String decoration) {
        super(ID, eventType, name, serviceType, employeeResponsible, nbOfHoursNeeded);
        this.decoration = decoration;
    }

    /**
     * Constructor for creating a new event with reading from our database
     * */
    public BusinessParty(int ID, String eventType, String name, String serviceType, String startDate, String endDate, int nbOfHoursNeeded, String decoration){
        super(ID, eventType, name, serviceType, startDate, endDate, nbOfHoursNeeded);
        this.decoration = decoration;
    }

    /**
     * Setting the decoration needed for the business party
     * @param decoration (String) - decoration needed for the business party*/
    public void setdecoration(String decoration) {
        this.decoration = decoration;
    }

    /**
     * Getting the decoration needed for the business party
     * @return decoration (String) - decoration needed for the business party*/
    public String getDecoration() {
        return this.decoration;
    }

    /**
     * Calculates the event price that the customer needs to pay in order for the event to be organized
     * @return price (double) - price of the whole event*/
    @Override
    public double calculateEventPrices() {
        return (basePartyPrice + getNbOfHoursNeeded() * Employee.getHourlyWage());
    }
}
