package src;

import src.users.Employee;

import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.*;

public class Screen {
    private static SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy '-' HH:mm");

    private static Scanner scn = new Scanner(System.in);

    public void Screen(){ }

    // TODO: fix methods below

    /**
     * prints nicely formatted header of the screen
     * @param heading (String) - message to be nicely formatted as a header
     */
    public static void showHeader(String heading) {
        System.out.println("___________________________");
        System.out.println("|        " + heading + "        |");
        System.out.println("===========================");
    }

    /**
     * prints nicely formatted header with today's date
     */
    public static void showHeader() {
        showHeader(ft.format(new Date()));
    }


    /**
     * prints formated header of application
     * Logo of HipHapOrg
     */
    public static void showLogo() {
        showHeader("HipHapOrg");
    }



    public static void showDashboardOptions(String name) {
        System.out.println("Main Page");
        System.out.println("==============================");
        System.out.println(name );
        System.out.println(ft.format(new Date()));
        System.out.println("==============================");
    }


    public static void clearScreen() {
        for (int i = 0; i < 30; i++)
            System.out.println();
    }

    public static void listOptions(boolean isManager) {
//        showMain(currentUser.getName());
        System.out.println("0. Logout");
        System.out.println("1. Add event");
        System.out.println("2. Change date");
        if (isManager) {
            System.out.println("3. Show all customers");
            System.out.println("4. Show all events");
            System.out.println("5. Show all employees");
        } else {
            System.out.println("3. Show my events");
            System.out.println("4. Show partners");
        }
    }

    public static void showEventForm() {
        System.out.print("Event name: ");
        String name = scn.nextLine();
        while (name.equals("")) {
            System.out.println("Event name cannot be empty. Please try again.");
            name = scn.nextLine();
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

        //TODO: calculate starting date of the new events
        // new events will be organized after the last events is finished
        //TODO: calculate how many hours an events takes to organize

    }


}

