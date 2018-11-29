package src;

import java.util.Scanner;

public class Helper {

    static Scanner scn = new Scanner(System.in);

    public static int selectOption ( int limit){ // there is a limit for options, the manager will have more options
        // so we can set the number of options it will accept as input
        // use this method for both employees and manager
        int selectedOption = 0;
        try {
            System.out.print("Option: ");
            selectedOption = scn.nextInt();
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


}
