package src;

import src.users.Employee;
import java.util.Scanner;

public class Application {

    // keep track of currently logged in Employee
    private Employee currentUser;

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

        System.out.println("Logged in successfully");
    }


//            if (loggedIn) {
//                if (currentUser.getID() == 9999) { // 9999 = manager ID
//                    // Program runs for the manager from now on
//                    // As long as he does not press the log out option, this loop will run
//                    // This way, you can choose eg. option 4, do whatever, then return to the dashboard screen
//                    // Then select eg. option 2, do whatever etc
//                } else {
//                    // Program runs for the employee from now on
//                    // As long as he does not press the log out option, this loop will run
//                    // This way, you can choose eg. option 4, do whatever, then return to the dashboard screen
//                    // Then select eg. option 2, do whatever etc
//                    while (loggedOut == false) {
//                        Screen.showDashboard(currentUser);
//
//                        switch (selectOption(6)) {
//                            case 1:
//                                Screen.showEventForm();
//
//                        }
//
//                    }
//
//                }
//            }
//        }


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



    // =======================
    // Login connected methods
    // =======================
    /**
     * validates Login information to see if the user exists and if he entered the correct password
     * */
    public void validateUser (String username, String password){
        // retrieve user data from db
        Employee user = db.getEmployee(username);

        // user doesn't exist
        if (user == null) {
            showLogin(Helper.USER_NOT_FOUND);
            return;
        }

        // login user if password correct
        if (user.getPassword().equals(password)) {
            login(user);
        } else {
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
        currentUser = null;
    }
}
