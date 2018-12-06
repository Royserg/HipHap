package src;

import src.events.BusinessParty;
import src.events.Conference;
import src.events.Event;
import src.events.Trip;
import src.users.Customer;
import src.users.Employee;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class Application {

    // keep track of currently logged in Employee
    private static Employee currentUser;

    //    private Scanner scn = new Scanner(System.in);
    private Database db = new Database();


    // constructor
    Application() { }


    /**
     * starts application
     */
    void run() {
        // show starting login page
        showLogin("");
    }

    // ==========================
    // screens of the application
    // ==========================
    /**
     * prints login screen info and asks
     * for credentials: username and password
     * @param errorMsg (String) - message to be displayed under header
     * */
    private void showLogin(String errorMsg) {
        String username;
        String password;

        Screen.showLogo();
        System.out.println(errorMsg);
        System.out.println();
        // get username
        username = Helper.getString("Username: ");
        // get password
        password = Helper.getString("Password: ");
        // authenticate user
        validateUser(username, password);
    }


    private void showDashboard() {
        Screen.clearScreen();
        Screen.showLogo();
        // showing header with default date
        Screen.showHeader();
        if (currentUser.getID() == 1111) {
            System.out.println("Logged in as the manager");
            System.out.println();
        }
        else {
            System.out.println("Logged in as " + currentUser.getName());
            System.out.println();
        }

        ArrayList<Integer> eventIDsForToday = db.getEmployeeEventsForToday(currentUser.getEventIDs());
        if (eventIDsForToday.isEmpty()) {
            System.out.println("No events to organize for today");
        }
        else {
            System.out.println("Events to organize for today:");
            // going through the selected events ids and comparing them to the events array
            for (int i = 0; i < eventIDsForToday.size(); i++)
                for (int k = 0; k < db.events.size(); k++)
                    if (eventIDsForToday.get(i) == db.events.get(k).getID()) {
                        System.out.print((i+1) + ". ");
                        //System.out.print("Event ID: " + db.events.get(k).getID() + "| ");
                        System.out.println(db.events.get(k).printInfo());
                        System.out.println();
                    }
        }
        //System.out.println();
        //System.out.println();

        String[] options = Screen.getOptions(currentUser.getID());
        // show menu options
        Screen.listOptions(options);
        // user inputs option number
        int selection = Helper.selectOption(options.length);
        // activate chosen option
        handleSelectedOption(options[selection]);
    }

    private void handleDateMenu(String action) {
        Screen.clearScreen();
        Screen.showLogo();
        if (action.equals("show options")) {
            // todo: those 4 calls are repeating - for making a method
            String[] options = Screen.getOptions("date options");
            // print options
            Screen.listOptions(options);
            // user inputs option number
            int selection = Helper.selectOption(options.length);

            handleSelectedOption(options[selection]);
            return;
        }

        // sub-screens of Date options
        if (action.equals("date")) {
            // user provides date to check
            Date date = Helper.getDate();

            ArrayList<Event> events = db.getEvents(date, currentUser.getID());

            Screen.clearScreen();
            Screen.showLogo();

            // display header with selected date
            DateFormat dateFormat = new SimpleDateFormat("    dd.MM.yyyy    ");
            Screen.showHeader(dateFormat.format(date));

            System.out.println("0. Main Menu");

            if (events.isEmpty()) {
                System.out.println("==== No Events ====");
            } else {
                for (int i = 0; i < events.size(); i++) {
                    System.out.println((i + 1) + ". " + events.get(i));
                }
            }

            int selectedEvent = Helper.selectOption(events.size());

            if (selectedEvent == 0) {
                showDashboard();
            } else {
                handleSelectedEvent(events.get(selectedEvent - 1));
            }

        } else if (action.equals("period")) {
            System.out.println("=== Start Date ===");
            Date startDate = Helper.getDate();

            System.out.println("=== End Date ===");
            Date endDate = Helper.getDate();

            // fetch events between specified dates
            ArrayList<Event> events = db.getEvents(startDate, endDate, currentUser.getID());

            Screen.clearScreen();
            Screen.showLogo();

            // display header with selected date period
            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            Screen.showHeader(dateFormat.format(startDate) + "-" + dateFormat.format(endDate));

            // show main menu option
            System.out.println("0. Main menu");

            if (events.isEmpty()) {
                System.out.println("==== No Events ====");
            } else {
                for (int i = 0; i < events.size(); i++) {
                    System.out.println((i + 1) + ". " + events.get(i));
                }
            }

            int selectedEvent = Helper.selectOption(events.size());

            if (selectedEvent == 0) {
                showDashboard();
            } else {
                handleSelectedEvent(events.get(selectedEvent - 1));
            }
        }
    }

    // this returns the option that you selected back to the selectEvent method and it handles it there
    private int showSelectEventMenu(){
        String[] options = Screen.getOptions("select event options");
        // print options
        Screen.listOptions(options);
        // user inputs option number
        int selection = Helper.selectOption(options.length-1);
        System.out.println("selected: " + options[selection]);
        return selection;
        // 0 - main menu
        // 1 - edit
        // 2 - delete
    }

    // ==================================
    // ======== Option selecting ========
    // ==================================
    private void handleSelectedOption(String option) {
        switch (option) {
            case Helper.LOGOUT:
                logout();
                break;
            case Helper.SHOW_DASHBOARD:
                showDashboard();
                break;
            case Helper.SELECT_DATE:
                handleDateMenu("date");
                break;
            case Helper.SELECT_PERIOD:
                handleDateMenu("period");
                break;
            case Helper.ADD_EVENT:
                System.out.println("Showing New Event Form");
                addEvent();
                Helper.getString("Press enter to go back to main menu");
                showDashboard();
                break;
            case Helper.CHANGE_DATE:
                handleDateMenu("show options");
                break;
            case Helper.SHOW_CUSTOMERS:
                System.out.println("Showing Customers");
                showCustomers();
                Helper.getString("Press enter to go back to main menu");
                showDashboard();
                break;
            case Helper.SHOW_EMPLOYEES:
                System.out.println("Showing Employees");
                showEmployees();
                Helper.getString("Press enter to go back to main menu");
                showDashboard();
                break;
            case Helper.SHOW_PARTNERS:
                System.out.println("Showing Partners");
                showPartners();
                Helper.getString("Press enter to go back to main menu");
                showDashboard();
                break;
            case Helper.SELECT_EVENT:
                System.out.println("Showing my events");
                selectEvent();
                Helper.getString("Press enter to go back to main menu");
                showDashboard();
                break;
            default:
                System.out.println("Option does not exist");
                Helper.getString("Press enter to go back to main menu");
                showDashboard();
                break;
        }
    }

    private void selectEvent() {
        System.out.println();

        // for manager
        if (currentUser.getID() == 1111) {
            for (int j = 0; j < db.employees.size(); j++) {
                // getting the events for this employee for today
                ArrayList<Integer> eventIDs = db.getEmployeeEventsForToday(db.employees.get(j).getEventIDs());
                //System.out.println(eventIDs);
                System.out.println((j +1) + ". Employee: " + db.employees.get(j).getName());

                // going through the selected events ids and comparing them to the events array
                for (int i = 0; i < eventIDs.size(); i++)
                    for (int k = 0; k < db.events.size(); k++)
                        if (eventIDs.get(i) == db.events.get(k).getID()) {
                            System.out.print((i+1) + ". ");
                            //System.out.print("Event ID: " + db.events.get(k).getID() + "| ");
                            System.out.println(db.events.get(k).printInfo());
                            System.out.println();
                        }
            }
            System.out.println("Select an employee whose events you want to modify");
            int selectedEmployee = Helper.selectOption(1, db.employees.size());

            Screen.clearScreen();
            ArrayList<Integer> eventIDs = db.getEmployeeEventsForToday(db.employees.get(selectedEmployee-1).getEventIDs());
            // checks whether there are events or not for today
            if (eventIDs.isEmpty()) {
                System.out.println(db.employees.get(selectedEmployee-1).getName() + " has no events for today");
                Helper.getString("Press any key to go back to main menu");
                showDashboard();
            }

            // if there are events, proceed
            else {
                System.out.println("Select one of " + db.employees.get(selectedEmployee-1).getName() + "'s events");
                // going through the selected events ids and comparing them to the events array
                for (int i = 0; i < eventIDs.size(); i++)
                    for (int k = 0; k < db.events.size(); k++)
                        if (eventIDs.get(i) == db.events.get(k).getID()) {
                            System.out.print((i+1) + ". ");
                            //System.out.print("Event ID: " + db.events.get(k).getID() + "| ");
                            System.out.println(db.events.get(k).printInfo());
                            System.out.println();
                        }

                int selectedEventNumber = Helper.selectOption(1, eventIDs.size());
                int selectedEventID = eventIDs.get(selectedEventNumber-1);

                int selectedOption = showSelectEventMenu();
                System.out.println(selectedOption);
                switch (selectedOption) {
                    case 0:
                        showDashboard();
                        break;
                    case 1:
                        System.out.println("Editing event");
                        System.out.println(selectedEventID);
                        //editEvent(selectedEventID);
                        Helper.getString("Press enter to go back to main menu");
                        showDashboard();
                        break;
                    case 2:
                        System.out.println("Deleting event");
                        System.out.println(selectedEventID);
                        //deleteEvent(selectedEventID);
                        Helper.getString("Press enter to go back to main menu");
                        showDashboard();
                        break;
                }
            }
        }

        // for employee
        else {
            ArrayList<Integer> eventIDs = db.getEmployeeEventsForToday(currentUser.getEventIDs());
            // if there are no events for today, you cannot choose anything
            if (eventIDs.isEmpty()) {
                System.out.println("No events for today");
                Helper.getString("Press enter to return to main menu");
                showDashboard();
            }

            // if there are events for today, proceed
            else {
                // going through the selected events ids and comparing them to the events array
                for (int i = 0; i < eventIDs.size(); i++)
                    for (int k = 0; k < db.events.size(); k++)
                        if (eventIDs.get(i) == db.events.get(k).getID()) {
                            System.out.print((i+1) + ". ");
                            //System.out.print("Event ID: " + db.events.get(k).getID() + "| ");
                            System.out.println(db.events.get(k).printInfo());
                            System.out.println();
                        }
                System.out.println("Select one of your events");
                int selectedEventNumber = Helper.selectOption(1, eventIDs.size());
                int selectedEventID = eventIDs.get(selectedEventNumber-1);
                System.out.println(selectedEventID);

                int selectedOption = showSelectEventMenu();
                System.out.println(selectedOption);
                switch (selectedOption) {
                    case 0:
                        showDashboard();
                        break;
                    case 1:
                        System.out.println("Editing event");
                        //editEvent(selectedEventID);
                        Helper.getString("Press enter to go back to main menu");
                        showDashboard();
                        break;
                    case 2:
                        System.out.println("Deleting event");
                        deleteEvent(selectedEventID);
                        Helper.getString("Press enter to go back to main menu");
                        showDashboard();
                        break;
                    default:
                        System.out.println("Wrong option");
                        Helper.getString("Press enter to go back to main menu");
                        showDashboard();
                        break;
                }
            }

        }

    }

    /**
     * Shows options for chosen event and manages selected operation on it
     * @param event (Event)
     */
    private void handleSelectedEvent(Event event) {
        // todo: improve it for printing events and choosing one of them

        int selectedOption = showSelectEventMenu();
        System.out.println(selectedOption);
        switch (selectedOption) {
            case 0:
                showDashboard();
                break;
            case 1:
                System.out.println("Editing event");
                editEvent(event.getID());
                Helper.getString("Press enter to go back to main menu");
                showDashboard();
                break;
            case 2:
                System.out.println("Deleting event");
                deleteEvent(event.getID());
                Helper.getString("Press enter to go back to main menu");
                showDashboard();
                break;
            default:
                System.out.println("Wrong option");
                Helper.getString("Press enter to go back to main menu");
                showDashboard();
                break;
        }

    }


    private void showPartners() {
        System.out.println();
        for (int i = 0; i < db.partners.size(); i++) {
            System.out.print(i+1 + ". Name: " + db.partners.get(i).getName() + "| ");
            System.out.println("Service: " + db.partners.get(i).getOccupation());
            System.out.println();
        }
    }

    private void showCustomers() {
        System.out.println();
        for (int i = 0; i < db.customers.size(); i++) {
            System.out.print("Name: " + db.customers.get(i).getName() + "| ");
            System.out.println("Event ID(s): " + db.customers.get(i).getOwnEvents());
        }
    }

    private void showEmployees() {
        System.out.println();
        for (int i = 0; i < db.employees.size(); i++) {
            System.out.print("Name: " + db.employees.get(i).getName() + "| ");
            System.out.println("Event ID(s): " + db.employees.get(i).getEventIDs());
        }
    }
    // =======================
    // Login connected methods
    // =======================
    /**
     * validates Login information to see if the user exists and if he entered the correct password
     * */
    private void validateUser (String username, String password){
        // retrieve user data from db
        Employee user = db.getEmployeeByName(username);

        // user doesn't exist
        if (user == null) {
            Screen.clearScreen();
            showLogin(Helper.USER_NOT_FOUND);
            return;
        }

        // login user if password correct
        if (user.getPassword().equals(password)) {
            db = new Database();
            login(user);
            showDashboard();
        } else {
            Screen.clearScreen();
            // authentication failed
            showLogin(Helper.PASSWORD_INCORRECT);
        }
    }

    /**
     * Save user into global attirbute currentUser, so it is easily accessed
     * @param user (Employee) - user to be logged in
     */
    private void login(Employee user) {
        currentUser = user;
    }

    /**
     * removes logged in user reference from currentUser attribute
     */
    private void logout() {
        // saves changes into the files
        db.writeCustomersFile();
        db.writeEmployeesFile();
        db.writeEventsFile();
        db.writePartnersFile();
        // remove user
        currentUser = null;
        // show login screen
        showLogin(Helper.LOGOUT_SUCCESS);
    }

    public static Employee getCurrentUser(){ return currentUser; }


    private static int generateRandomID(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    /**
     * Creates new event based on user inputed information*/
    private void addEvent(){
        Employee responsibleEmployee;
        int newID = 0;
        String eventTypeString = "";
        String serviceTypeString = "";
        int nbOfHoursNeeded;

        //generating new ID
        boolean uniqueID = false;
        //check if the ID exists
        while (!uniqueID) {
            newID = generateRandomID(1000, 9999);
            int i = 0;

            while (!uniqueID) {
                if (db.events.get(i).getID() != newID)
                    uniqueID = true;
                else if (i == (db.events.size() - 1))
                    break;
                i++;
            }
        }

        //Event name
        String name = Helper.getString("Event name: ");
        while (name.equals("")) {
            name = Helper.getString("Event name cannot be empty. Please try again.");
        }
        System.out.println();
        System.out.println();

        //Selecting event type
        System.out.println("Event type: ");
        System.out.println("1 - Conference");
        System.out.println("2 - Trip");
        System.out.println("3 - Business Party");
        int eventType = Helper.selectOption(1, 3);
        System.out.println();
        System.out.println();

        //Select event service type
        System.out.println("Event service: ");
        System.out.println("1 - Consultancy");
        System.out.println("2 - Planning");
        System.out.println("3 - Full Organization");
        int serviceType = Helper.selectOption(1, 3);
        System.out.println();
        System.out.println();

        if (serviceType == 1){
            serviceTypeString = "Consultancy";
        }else if (serviceType == 2){
            serviceTypeString = "Planning";
        }else if(serviceType == 3){
            serviceTypeString = "Full Organization";
        }

        //Selecting a customer
        System.out.println("Select customer that is ordering this event, if it's not on the list, select 0 to create a new customer.");
        System.out.println("0 - Add new customer");
        for (int i = 0; i < db.customers.size(); i++){
            System.out.println( i+1 + " - " + db.customers.get(i).getName());
        }
        int customerSelect = Helper.selectOption(db.customers.size() + 1);

        //creating new customer or adding eventID to an existing customer
        if( customerSelect == 0){
            String customerName = Helper.getString("Enter customer's name: ");
            db.customers.add( new Customer(newID, customerName));
        } else {
            db.customers.get(customerSelect - 1).addEvent(newID);
        }
        System.out.println();
        System.out.println();

        System.out.println("== Enter start date of the event ==");
        Date startOFEvent = Helper.getDate();

        System.out.println();
        System.out.println();

        //adding partners
        System.out.println("If the partners are needed for the event select the partners. If they are not needed, select 0");
        int currentPartner = 1;
        ArrayList<Integer> allPartners = new ArrayList<>();
        while (currentPartner != 0){
            System.out.println("0 - no additional partners");
            for(int i = 0; i < db.partners.size(); i++)
                System.out.println(i+1 + " - " + db.partners.get(i).getName());
            currentPartner = Helper.selectOption(db.partners.size() + 1);
            if(currentPartner != 0)
                allPartners.add(db.partners.get(currentPartner-1).getID());
        }
        System.out.println();
        System.out.println();

        //assigning employee
        if (currentUser.getID() == 1111){
            int employeeID = Helper.getInt("Enter ID of the employee that is going to be responsible for this event: ");
            responsibleEmployee = db.getEmployeeByID(employeeID);
        }else{
            responsibleEmployee = currentUser;
        }
        //adding event ID to the employee
        for(int i = 0; i<db.employees.size(); i++){
            if(responsibleEmployee.getID() == db.employees.get(i).getID())
                db.employees.get(i).addEvent(newID);
        }


        nbOfHoursNeeded = Helper.getInt("Enter the number of hours needed to organize this event: ");
        System.out.println();
        System.out.println();

        if(eventType == 1){
            String officeSupplies = Helper.getString("Enter needed office supplies: ");
            eventTypeString = "Conference";
            Conference newEvent = new Conference(newID, eventTypeString, name, serviceTypeString, startOFEvent, responsibleEmployee, nbOfHoursNeeded, officeSupplies);
            for(int i = 0; i < allPartners.size(); i++ )
                newEvent.addPartner(allPartners.get(i));
            newEvent.calculateEventPrices();
            System.out.println();
            System.out.println();
            System.out.println("Total event price is: " + newEvent.getTotalConferencePrice() + "kr");
            db.events.add(newEvent);
        }else if (eventType == 2){
            String transport = Helper.getString("Enter type of transportation needed for the trip: ");
            eventTypeString = "Trip";
            Trip newEvent = new Trip(newID, eventTypeString, name, serviceTypeString, startOFEvent, responsibleEmployee, nbOfHoursNeeded, transport);
            for(int i = 0; i < allPartners.size(); i++ )
                newEvent.addPartner(allPartners.get(i));
            newEvent.calculateEventPrices();
            System.out.println();
            System.out.println();
            System.out.println("Total event price is: " + newEvent.getTotalTripPrice() + "kr");
            db.events.add(newEvent);
        }else if (eventType == 3){
            String decoration = Helper.getString("Enter decoration needed for the party: ");
            eventTypeString = "Business Party";
            BusinessParty newEvent = new BusinessParty(newID, eventTypeString, name, serviceTypeString, startOFEvent, responsibleEmployee, nbOfHoursNeeded, decoration);
            for(int i = 0; i < allPartners.size(); i++ )
                newEvent.addPartner(allPartners.get(i));
            newEvent.calculateEventPrices();
            System.out.println();
            System.out.println();
            System.out.println("Total event price is: " + newEvent.getTotalPartyPrice()  + "kr");
            db.events.add(newEvent);
        }
    }

    private void editEvent(int eventID){
        Event event = db.getEventByID(eventID);
        int optionSelected = 1;
        while (optionSelected != 0) {
            System.out.println("Select which attribute you want to modify or press 0 if you don't want to modify anything else");
            System.out.println("0 - nothing else to modify, go back to main menu");
            System.out.println("1 - Edit name");
            System.out.println("2 - Edit service type");
            System.out.println("3 - Edit specifications, items needed to organize the event");
            System.out.println("4 - Edit partners");
            optionSelected = Helper.selectOption(5);

            if (optionSelected == 1){
                System.out.println("Current name is: " + event.getName());
                event.setName(Helper.getString("Enter a new name"));
            }else if (optionSelected == 2){
                System.out.println("Current service type is: " + event.getServiceType());
                System.out.println("Event service: ");
                System.out.println("1 - Consultancy");
                System.out.println("2 - Planning");
                System.out.println("3 - Full Organization");
                int serviceType = Helper.selectOption(1, 3);

                if (serviceType == 1){
                    event.setServiceType("Consultancy");
                }else if (serviceType == 2){
                    event.setServiceType("Planning");
                }else if(serviceType == 3){
                    event.setServiceType("Full Organization");
                }
            }else if (optionSelected == 3){
                System.out.println("Current specifications are: " + event.getSpecs());
                event.setSpecs(Helper.getString("Enter all of the specifications needed for the event."));
            }else if (optionSelected == 4){
                System.out.println("Current partners are: ");
                ArrayList<Integer> partnerIDs = event.getPartnersIDs();
                for(int i = 0; i < partnerIDs.size(); i++){
                    System.out.println(i+1 + db.getPartnerByID(partnerIDs.get(i)).getName());
                }
                System.out.println("Select 0 to add  a partner or a number in front of the partner you want to delete");
                System.out.println("0 - add a partner");
                for(int i = 0; i< partnerIDs.size(); i++){
                    System.out.println(i+1 + db.getPartnerByID(partnerIDs.get(i)).getName());
                }
                int partnerSelected = Helper.selectOption(partnerIDs.size()+1);

                //adding a partner
                if(partnerSelected == 0){
                    for(int i = 0; i < db.partners.size(); i++)
                        System.out.println(i+1 + " - " + db.partners.get(i).getName());
                    int newPartner = Helper.selectOption(db.partners.size() + 1);
                    partnerIDs.add(db.partners.get(newPartner-1).getID());
                }else{ //deleting a partner
                    for(int i = 0; i < db.partners.size(); i++)
                        System.out.println(i+1 + " - " + db.partners.get(i).getName());
                    int delPartner = Helper.selectOption(1, db.partners.size());
                    partnerIDs.remove(delPartner-1);
                }
            }
        }
    }

    public void deleteEvent(int eventID) {
        System.out.println(eventID);
        int employeeID = 0;

        for (int i = 0; i < db.events.size(); i++) {
            if (eventID == db.events.get(i).getID()) {
                // getting this employee's ID
                try {
                    /*
                    Event test = db.getEventByID(eventID);
                    System.out.println(test.getName());
                    Employee test2 = test.getEmployee();
                    System.out.println(test2.getName());
                    int test3 = test2.getID();
                    System.out.println(test3);
                    */
                    employeeID = db.getEventByID(eventID).getEmployee().getID();
                    System.out.println(employeeID);
                }
                catch(Exception e) {
                    System.out.println("Exception");
                }


                // searching his ID in the employees ArrayList
                for (int j = 0; j < db.employees.size(); j++) {
                    if (db.employees.get(j).getID() == employeeID) {
                        db.employees.get(j).removeEventID(eventID);
                    }
                    System.out.println(j + " try");
                }

                db.events.remove(i);
                System.out.println("Event deleted");


                break;
            }
        }

    }
}
