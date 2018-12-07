package src;

import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.*;


public class Screen {
    private static SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy '-' HH:mm");

    private static Scanner scn = new Scanner(System.in);

    public void Screen(){ }

    /**
     * prints nicely formatted header of the screen
     * @param heading (String) - message to be nicely formatted as a header
     */
    public static void showHeader(String heading) {
        System.out.println("===============================");
        System.out.println("|     " + heading + "   |");
        System.out.println("===============================");
    }

    /**
     * prints nicely formatted header with today's date
     */
    public static void showHeader() {
        showHeader(" " + ft.format(new Date()) + "  ");
    }


    /**
     * prints formated header of application
     * Logo of HipHapOrg
     */
    public static void showLogo() {
        showHeader("     HipHapOrg       ");
    }


    public static void clearScreen() {
        for (int i = 0; i < 20; i++)
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
        } else if (screen.equals("select event options")) {
            return Helper.arrayJoinString(defaultOptions, "Edit event, Delete event");
        } else if (screen.equals("edit event")){
            String[] editEventOptions = { "Nothing else to modify, go back to main menu", "Edit name", "Edit service type", "Edit specifications, items needed to organize the event", "Edit partners" };
            return editEventOptions;
        }

        return defaultOptions;
    }

    public static void listOptions(String[] options) {
        System.out.println("____________________");
        for (int i = 0; i < options.length; i++) {
            System.out.println(i + ". " + options[i]);
        }
        // add extra spacing after options
        System.out.println();
        System.out.println();
    }

}

