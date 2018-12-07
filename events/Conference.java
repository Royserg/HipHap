package src.events;

import src.users.Employee;

import java.util.Date;

public class Conference extends Event implements EventPricing{
    private  double baseConferencePrice = 4000 ;
    private double totalConferencePrice;

    /**
     * Constructor for creating a whole new conference object by employee*/
    public Conference(int ID, String eventType, String name, String serviceType, Date startOfEvent, Employee employeeResponsible, int nbOfHoursNeeded, String specs) {
        super(ID, eventType, name, serviceType, startOfEvent, employeeResponsible, nbOfHoursNeeded, specs);
    }

    /**
     * Constructor for creating a new conference with reading from our database*/
    public Conference(int ID, String eventType, String name, String serviceType, Employee employee, String startDate, String endDate, String startOfEvent, int nbOfHoursNeeded, String specs, String partnerIDs){
        super(ID, eventType, name, serviceType, employee, startDate, endDate, startOfEvent, nbOfHoursNeeded, specs, partnerIDs);

    }

    //Setters

    /**
     * Sets base conference price
     * @param price (double) - base price for the conference*/
    public void setBaseConferencePrice(double price) {this.baseConferencePrice = price;}

    //getters

    /**
     * Returns base conference price
     * @return baseConferencePrice (double) - base price for the conference */
    public double getBaseConferencePrice() { return this.baseConferencePrice; }

    /**
     * Returns total conference price that a customer should pay for the event
     * @return  totalConferencePrice (double) - price that a customer should pay for the event*/
    public double getTotalConferencePrice() { return this.totalConferencePrice; }

    /**
     * Calculates total conference price based on base price, number of hours needed to organize, hourly wage and number of additional specifications*/
    @Override
    public void calculateEventPrices() {
        int nbOfSpecs = super.specs.size();
        totalConferencePrice = (baseConferencePrice + getNbOfHoursNeeded() * Employee.getHourlyWage() + nbOfSpecs * 400);
    }
}