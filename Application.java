package src;

import src.events.BusinessParty;
import src.events.Conference;
import src.events.Event;
import src.events.Trip;
import src.users.Employee;

import java.util.Random;


public class Application {

    // keep track of currently logged in Employee
    //TODO: I need this to be static but is it okay as we can login multiple times ? - Aneja
    private static Employee currentUser;

//    private Scanner scn = new Scanner(System.in);
    private Database db = new Database();


    // constructor
    public Application() { }


    /**
     * starts application
     */
    public void run() {

        // TODO: think about that while loop
        // while (true) {

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
        // pass current date to the header
        Screen.showHeader();
        // TODO: list events for today
        db.getEmployeeEvents(currentUser.getEventIDs());

        String[] options = Screen.getOptions(currentUser.getID());
        // show menu options
        Screen.listOptions(options);
        // user inputs option number
        int selection = Helper.selectOption(options.length);
        // activate chosen option
        handleSelectedOption(options[selection]);
    }

    // ==================================
    // ======== Option selecting ========
    // ==================================
    private void handleSelectedOption(String option) {
        switch (option) {
            case Helper.LOGOUT:
                logout();
                break;
            case Helper.ADD_EVENT:
                System.out.println("Showing New Event Form");
                break;
            case Helper.CHANGE_DATE:
                System.out.println("Show changing date options");
                break;
            case Helper.SHOW_ALL_EVENTS:
                System.out.println("Showing all events");
                break;
            case Helper.SHOW_CUSTOMERS:
                System.out.println("Showing Customers");
                break;
            case Helper.SHOW_EMPLOYEES:
                System.out.println("Showing Employees");
                break;
            case Helper.SHOW_PARTNERS:
                System.out.println("Showing partners");
                break;
            case Helper.SHOW_MY_EVENTS:
                System.out.println("Showing my events");
                break;
            default:
                System.out.println("Option does not exist");
                break;
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
        // TODO: save information into files
        // remove user
        currentUser = null;
        // show login screen
        showLogin(Helper.LOGOUT_SUCCESS);
    }

    public static Employee getCurrentUser(){ return currentUser; }


    public static int generateRandomID(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public void addEvent(){
        Employee currentUser = Application.getCurrentUser();
        Employee responsibleEmployee;
        int newID = 0;
        String eventTypeString = "";
        String serviceTypeString = "";
        int nbOfHoursNeeded;

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
            }
        }

        String name = Helper.getString("Event name: ");
        while (name.equals("")) {
            name = Helper.getString("Event name cannot be empty. Please try again.");
        }

        System.out.println("Event type: ");
        System.out.println("1 - Conference");
        System.out.println("2 - Trip");
        System.out.println("3 - Business Party");
        int eventType = Helper.selectOption(3);

        System.out.println("Event service: ");
        System.out.println("1 - Consultancy");
        System.out.println("2 - Planning");
        System.out.println("3 - Full Organization");
        int serviceType = Helper.selectOption(3);

        if (serviceType == 1){
            serviceTypeString = "Consultancy";
        }else if (serviceType == 2){
            serviceTypeString = "Planning";
        }else if(serviceType == 3){
            serviceTypeString = "Full Organization";
        }

        if (currentUser.getID() == 1111){
            int employeeID = Helper.getInt("Enter ID of the employee that is going to be responsible for this event: ");
            responsibleEmployee = db.getEmployeeByID(employeeID);
        }else{
            responsibleEmployee = currentUser;
        }

        nbOfHoursNeeded = Helper.getInt("Enter the number of hours needed to organize this event: ");

        if(eventType == 1){
            String officeSupplies = Helper.getString("Enter needed office supplies: ");
            eventTypeString = "Conference";
            Conference newEvent = new Conference(newID, eventTypeString, name, serviceTypeString, responsibleEmployee, nbOfHoursNeeded, officeSupplies);
            db.events.add(newEvent);
        }else if (eventType == 2){
            String transport = Helper.getString("Enter type of transportation needed for the trip: ");
            eventTypeString = "Trip";
            Trip newEvent = new Trip(newID, eventTypeString, name, serviceTypeString, responsibleEmployee, nbOfHoursNeeded, transport);
            db.events.add(newEvent);
        }else if (eventType == 3){
            String decoration = Helper.getString("Enter decoration needed for the party: ");
            eventTypeString = "Business Party";
            BusinessParty newEvent = new BusinessParty(newID, eventTypeString, name, serviceTypeString, responsibleEmployee, nbOfHoursNeeded, decoration);
            db.events.add(newEvent);
        }


    }
}
