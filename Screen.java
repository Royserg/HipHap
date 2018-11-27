package com.company;

import java.util.Scanner;

public class Screen {

    private static Scanner scn = new Scanner(System.in);

    /**
     * prints formated header of application
     * Logo of HipHapOrg
     */
    private static void showLogo() {
        System.out.println("___________________________");
        System.out.println();
        System.out.println("|        HipHapOrg        |");
        System.out.println("___________________________");
    }

    /**
     * prints login screen info and asks
     * for credentials: username and password
     */
    public static void showLogin() {
        String username;
        String password;

        showLogo();

        System.out.println("========== Login ==========");
        System.out.println();

        System.out.print("Username: ");
        username = scn.nextLine();

        System.out.print("Password: ");
        password = scn.nextLine();

        //TODO: validate credentails from Screen level or in Main?

    }

    public static void showMain() {
        System.out.println("========== Main Page ==========");
        System.out.println("========== 26.11.2018 ==========");
        System.out.println("1. ");
        System.out.println("=== Main page ===");
        System.out.println("=== Main page ===");
    }
}

