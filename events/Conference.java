package src.events;

import src.users.Employee;

public class Conference extends Event implements EventPricing{
    private static double baseConferencePrice = 10000 ;

    /**
     * Constructor for creating a whole new event by employee
     * */
    public Conference(int ID,  String eventType, String name, String serviceType, Employee employeeResponsible, int nbOfHoursNeeded, String specs) {
        super(ID, eventType, name, serviceType, employeeResponsible, nbOfHoursNeeded, specs);
    }

    /**
     * Constructor for creating a new event with reading from our database
     * */
    public Conference(int ID, String eventType, String name, String serviceType, String startDate, String endDate, int nbOfHoursNeeded, String specs){
        super(ID, eventType, name, serviceType, startDate, endDate, nbOfHoursNeeded, specs);
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



    @Override
    public double calculateEventPrices() {
        int nbOfSpecs = super.specs.size();
        return (baseConferencePrice + getNbOfHoursNeeded() * Employee.getHourlyWage() + nbOfSpecs * 400);
    }
}