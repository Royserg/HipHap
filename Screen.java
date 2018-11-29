package src;

import src.users.Employee;

import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.*;

public class Screen {
    private static SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy '-' HH:mm");

    private static Scanner scn = new Scanner(System.in);

    public void Screen(){

    }

    // TODO: fix methods below


    private static void showHeader(String heading) {
        System.out.println("___________________________");
        System.out.println("|        " + heading + "        |");
        System.out.println("===========================");
    }


    /**
     * prints formated header of application
     * Logo of HipHapOrg
     */
    public static void showLogo() {
        showHeader("HipHapOrg");
    }



    public static void showMain(String name) {
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

    public static void showDashboard(Employee currentUser) {
        showMain(currentUser.getName());
        System.out.println("1. Add events");
        System.out.println("2. Change date");
        System.out.println("3. Show customers");
        System.out.println("4. Show my events");
        System.out.println("5. Show partners");
        System.out.println("6. Log out");
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

