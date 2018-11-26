package com.company;

import java.util.Scanner;

public class Screen {

    private static Scanner scn = new Scanner(System.in);

    /**
     * prints login screen info and asks
     * for credentials: username and password
     */
    public static void showLogin() {
        String username;
        String password;

        System.out.println("========== Login ==========");
        System.out.println();

        System.out.print("Username: ");
        username = scn.nextLine();

        System.out.print("Password: ");
        password = scn.nextLine();

        //TODO: validate credentails from Screen level or in Main?

    }
}