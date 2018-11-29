package src;

import java.util.Scanner;

public class Helper {

    static final String USER_NOT_FOUND = ">>>>>  User not found <<<<<";
    static final String PASSWORD_INCORRECT = ">>>> Incorrect password  <<<<";

    static Scanner scn = new Scanner(System.in);

    /**
     * Choosing specified option between 0 and provided limit
     * @param limit (int) upper limit of possible integer input
     * @return
     */
    public static int selectOption (int limit){
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
