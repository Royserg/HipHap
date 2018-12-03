package src;

import src.users.Employee;

import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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
        Screen.showLogo();
        Screen.showHeader();
        ArrayList<Integer> eventIDsForToday = db.getEmployeeEventsForToday(currentUser.getEventIDs());
        if (eventIDsForToday.isEmpty()) {
            System.out.println("No events for today");
        }
        else {
            System.out.println("Today's events:");
            for (int i = 0; i < eventIDsForToday.size(); i++) {
                System.out.print("Event ID: " + db.events.get(i).getID() + "| ");
                System.out.print("Name: " + db.events.get(i).getName() + "| ");
                System.out.print("Event Type: " + db.events.get(i).getEventType() + "| ");
                System.out.println("Service Type: " + db.events.get(i).getServiceType() + "| ");
            }
        }
        System.out.println();
        System.out.println();

        String[] options = Screen.getOptions(currentUser.getID());
        // show menu options
        Screen.listOptions(options);
        // user inputs option number
        int selection = Helper.selectOption(options.length);
        // activate chosen option
        handleSelectedOption(options[selection]);
    }

    private void showDateMenu() {
        String[] options = Screen.getOptions("date options");
        // print options
        Screen.listOptions(options);
        // user inputs option number
        int selection = Helper.selectOption(options.length);
        System.out.println("selected: " + options[selection]);
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
            case Helper.ADD_EVENT:
                System.out.println("Showing New Event Form");
                break;
            case Helper.CHANGE_DATE:
                showDateMenu();
                break;
            case Helper.SHOW_CUSTOMERS:
                System.out.println("Showing Customers");
                showCustomers();
                Helper.getString("Press any key to go back to main menu");
                Screen.clearScreen();
                showDashboard();
                break;
            case Helper.SHOW_EMPLOYEES:
                System.out.println("Showing Employees");
                break;
            case Helper.SHOW_PARTNERS:
                System.out.println("Showing Partners");
                showPartners();
                Helper.getString("Press any key to go back to main menu");
                Screen.clearScreen();
                showDashboard();
                break;
            case Helper.SELECT_EVENT:
                System.out.println("Showing my events");
                selectEvent();
                Helper.getString("Press any key to go back to main menu");
                Screen.clearScreen();
                showDashboard();
                break;
            default:
                System.out.println("Option does not exist");
                break;
        }
    }

    public void selectEvent() {
        System.out.println();

        if (currentUser.getID() == 1111) {
            for (int j = 0; j < db.employees.size(); j++) {
                ArrayList<Integer> eventIDs = db.getEmployeeEventsForToday(db.employees.get(j).getEventIDs());
                System.out.println("Employee: " + db.employees.get(j).getName());
                for (int i = 0; i < eventIDs.size(); i++) {
                    System.out.print("Event ID: " + db.events.get(i).getID() + "| ");
                    System.out.print("Name: " + db.events.get(i).getName() + "| ");
                    System.out.print("Event Type: " + db.events.get(i).getEventType() + "| ");
                    System.out.println("Service Type: " + db.events.get(i).getServiceType() + "| ");
                    System.out.print("Org Start Date: " + new SimpleDateFormat("dd.MM.yyyy 'at' HH").format(db.events.get(i).getOrgStartDate()) + "| ");
                    System.out.println("Org End Date: " + new SimpleDateFormat("dd.MM.yyyy 'at' HH").format(db.events.get(i).getOrgEndDate()) + "| ");
                    System.out.println();
                }
            }
        }

        else {
            ArrayList<Integer> eventIDs = db.getEmployeeEventsForToday(currentUser.getEventIDs());
            for (int i = 0; i < eventIDs.size(); i++) {
                System.out.print("Event ID: " + db.events.get(i).getID() + "| ");
                System.out.print("Name: " + db.events.get(i).getName() + "| ");
                System.out.print("Event Type: " + db.events.get(i).getEventType() + "| ");
                System.out.println("Service Type: " + db.events.get(i).getServiceType() + "| ");
                System.out.print("Org Start Date: " + new SimpleDateFormat("dd.MM.yyyy 'at' HH").format(db.events.get(i).getOrgStartDate()) + "| ");
                System.out.println("Org End Date: " + new SimpleDateFormat("dd.MM.yyyy 'at' HH").format(db.events.get(i).getOrgEndDate()) + "| ");
                System.out.println();
            }
        }

    }

    private void showPartners() {
        System.out.println();
        for (int i = 0; i < db.partners.size(); i++) {
            System.out.print("Name: " + db.partners.get(i).getName() + "| ");
            System.out.println("Service: " + db.partners.get(i).getOccupation());
        }
    }

    private void showCustomers() {
        System.out.println();
        for (int i = 0; i < db.customers.size(); i++) {
            System.out.print("Name: " + db.customers.get(i).getName() + "| ");
            System.out.println("Event ID(s): " + db.customers.get(i).getOwnEvents());
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
}
