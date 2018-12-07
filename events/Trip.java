package src.events;

import src.users.Employee;

import java.util.Date;

public class Trip extends Event implements EventPricing{
    private double baseTripPrice = 20000;
    private double totalTripPrice;


    /**
     * Constructor for creating a whole new trip object by employee*/
    public Trip(int ID, String eventType, String name, String serviceType, Date startOfEvent, Employee employeeResponsible, int nbOfHoursNeeded, String specs) {
        super(ID, eventType, name, serviceType, startOfEvent, employeeResponsible, nbOfHoursNeeded, specs);
    }

    /**
     * Constructor for creating a new trip with reading from our database*/
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