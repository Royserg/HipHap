package com.company;

import java.util.Scanner;

public class Screen {

    private static Scanner scn = new Scanner(System.in);

    /**
     * Showing initial login screen when application run
     */
    public static void showLogin() {
        String username;
        String password;

        System.out.println("HipHapOrg");
        System.out.println("======= Login =======");
        System.out.println();

        System.out.print("Username: ");
        username = scn.nextLine();

        System.out.print("Password: ");
        password = scn.nextLine();

        //TODO: implement validating info here or in Main
    }

}
