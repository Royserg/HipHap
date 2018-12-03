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
        System.out.println("|    " + heading + "   |");
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
        showHeader("    HipHapOrg     ");
    }


    public static void clearScreen() {
        for (int i = 0; i < 30; i++)
            System.out.println();
    }

    /**
     * get available main menu options for the provided user
     * @param userID id of the user for whom options should be returned
     * @return [String[]] array of Strings - available options for a particular user
     */
    public static String[] getOptions(int userID) {
        // check if user is manager from provided id
        boolean isManager = userID == 1111;

        // default main options - employee
        String[] mainOptions = {"Logout", "Add event", "Change date", "Select event", "Show partners"};
        // options for the dashboard - manager
        String[] mainManagerOptions = Helper.arrayJoinString(mainOptions, "Show customers, Show employees");

        return isManager ? mainManagerOptions: mainOptions;
    }

    /**
     * get available options for the screen
     * @param screen [String] for what screen options should be returned
     * @return [String[]] options for particular screen specified
     */
    public static String[] getOptions(String screen) {

        String[] defaultOptions = {"Main menu"};

        // options for Date options screen
        if (screen.equals("date options")) {
            return Helper.arrayJoinString(defaultOptions, "Select date, Select period");
        }

        return defaultOptions;
    }

    public static void listOptions(String[] options) {
        for (int i = 0; i < options.length; i++) {
            System.out.println(i + ". " + options[i]);
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

