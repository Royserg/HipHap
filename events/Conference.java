package src.events;

import src.users.Employee;

public class Conference extends Event implements EventPricing{
    private  double baseConferencePrice = 4000 ;
    private double totalConferencePrice;

    /**
     * Constructor for creating a whole new event by employee
     * */
    public Conference(int ID,  String eventType, String name, String serviceType, String startOfEvent, Employee employeeResponsible, int nbOfHoursNeeded, String specs) {
        super(ID, eventType, name, serviceType, startOfEvent, employeeResponsible, nbOfHoursNeeded, specs);
    }

    /**
     * Constructor for creating a new event with reading from our database
     * */
/*
    public Conference(int ID, String eventType, String name, String serviceType, Employee employee, String startDate, String endDate, int nbOfHoursNeeded, String specs){
        super(ID, eventType, name, serviceType, employee, startDate, endDate, nbOfHoursNeeded, specs);
*/
    public Conference(int ID, String eventType, String name, String serviceType, Employee employee, String startDate, String endDate, String startOfEvent, int nbOfHoursNeeded, String specs, String partnerIDs){
        super(ID, eventType, name, serviceType, employee, startDate, endDate, startOfEvent, nbOfHoursNeeded, specs, partnerIDs);

    }

    //Setters

    /**
     * set base conference price
     * @param price (double) - base price for the conference
     */
    public void setBaseConferencePrice(double price) {this.baseConferencePrice = price;}

    //getters

    /**
     * returns base conference price
     * @return baseConferencePrice (double) - base price for the conference
     */
    public double getBaseConferencePrice() { return this.baseConferencePrice; }

    public double getTotalConferencePrice() { return this.totalConferencePrice; }

    @Override
    public void calculateEventPrices() {
        int nbOfSpecs = super.specs.size();
        totalConferencePrice = (baseConferencePrice + getNbOfHoursNeeded() * Employee.getHourlyWage() + nbOfSpecs * 400);
    }
}