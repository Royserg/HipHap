package src;

import src.users.Employee;
import java.util.Scanner;

public class Application {

    // keep track of currently logged in Employee
    private Employee currentUser;

    private Scanner scn = new Scanner(System.in);
    private Database db = new Database();

    // constructor
    public Application() { }

    /* TODO: check below and fix === */

    public void run() {

        // infinite loop so you could log in and out as many times as you want
        while (true) {
            // show login page
            Screen.showLogin();



//            boolean loggedIn = Screen.showLogin();
//            boolean loggedOut = false;
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
        }
    }

        /*
         * validates Login information to see if the user exists and if he entered the correct password
         * */
        public boolean validateUser (String username, String password){

            // TODO: Database method -> Employee getEmployee(String name);

            boolean validation = false;
            int i = 0;
            int x = 0;
            boolean found = false;

            for (i = 0; i < db.employees.size(); i++) {
                if (db.employees.get(i).getName().equals(username)) {
                    found = true;
                    x = i;
                    break;
                }
            }

            if (db.employees.get(x).getPassword().equals(password)) {
                // Saving the employee information into the currentUser object
                currentUser = db.employees.get(x);
                validation = true;
            }

            return validation;
        }

        private void loginUser(Employee user) {

        }


}
