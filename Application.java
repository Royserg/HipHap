package src;

import src.users.Employee;
import java.util.Scanner;

public class Application {

    // keep track of currently logged in Employee
    private Employee currentUser;

    private Scanner scn = new Scanner(System.in);
    private Database db = new Database();

    // constructor
    public Application() {
    }

    /* TODO: check below and fix === */

    public void run() {

        // infinite loop so you could log in and out as many times as you want
//        while (true) {
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
//        }
    }

        /*
         * validates Login information to see if the user exists and if he entered the correct password
         * */
//        public static boolean validateLogin (String username, String password){
//            boolean validation = false;
//            int i = 0;
//            int x = 0;
//            boolean found = false;
//
//            for (i = 0; i < employees.size(); i++) {
//                if (employees.get(i).getName().equals(username)) {
//                    found = true;
//                    x = i;
//                    break;
//                }
//            }
//
//            if (employees.get(x).getPassword().equals(password)) {
//                // Saving the employee information into the currentUser object
//                currentUser = employees.get(x);
//                validation = true;
//            }
//
//            return validation;
//        }
//
//        public static int selectOption ( int limit){ // there is a limit for options, the manager will have more options
//            // so we can set the number of options it will accept as input
//            // use this method for both employees and manager
//            int selectedOption = 0;
//            try {
//                System.out.print("Option: ");
//                selectedOption = scn.nextInt();
//            } catch (Exception e) {
//                System.out.print("Input error occurred: " + e.getMessage());
//            }
//
//
//            if ((selectedOption > 0) && (selectedOption < limit + 1))
//                return selectedOption;
//
//            else {
//                System.out.println("Wrong option. Please try again.");
//                return selectOption(limit);
//            }
//        }


}
