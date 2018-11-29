package src;

import java.util.Scanner;

public class Helper {

    static Scanner scn = new Scanner(System.in);

    /**
     * Choosing specified option with a limit
     * @param limit (int) accepting integer to provided limit
     * @return
     */
    public static int selectOption (int limit){ // there is a limit for options, the manager will have more options
        // so we can set the number of options it will accept as input
        // use this method for both employees and manager
        int selectedOption = 0;

        try {
            selectedOption = getInt("Option: ");
        } catch (Exception e) {
            System.out.print("Input error occurred: " + e.getMessage());
        }


        if ((selectedOption > 0) && (selectedOption < limit + 1))
            return selectedOption;

        else {
            System.out.println("Wrong option. Please try again.");
            return selectOption(limit);
        }
    }

    /**
     * Prints provided message and waits for String from user
     * @param msg (String) - message to be displayed
     * @return user input (String)
     */
    public static String getString(String msg) {
        System.out.print(msg);
        String inputString = scn.nextLine();
        return inputString;
    }

    /**
     * Prints provided message and waits for integer from user
     * @param msg (String) - message to be displayed
     * @return user input (int)
     */
    public static int getInt(String msg) {
        System.out.print(msg);
        int inputInt = scn.nextInt();

        // consume rest of the line after int
        scn.next();

        return inputInt;
    }

}
