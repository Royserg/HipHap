package com.company;

import java.io.IOError;
import java.io.IOException;
import java.util.Scanner;

public class Screen {

    private static Scanner scn = new Scanner(System.in);

    public void Screen(){

    }

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
     * credentials are validated in the main
     */
    public static boolean showLogin() {
        String username = null;
        String password = null;

        showLogo();

        System.out.println("========== Login ==========");
        System.out.println();

        System.out.print("Username: ");
        // TODO: check if this error check is nessesary

        try {
            username = scn.nextLine();
        }catch (IOError e){
            System.out.print("IOError occured: " + e.getMessage());
            showLogin();//called to show login screen again and the user can input again
        }

        System.out.print("Password: ");
        try {
            password = scn.nextLine();
        }catch (IOError e){
            System.out.print("IOError occured: " + e.getMessage());
            showLogin();//called to show login screen again and the user can input again
        }


        if (Main.validateLogin(username, password) == false) {
            System.out.println("Validation Error: username or password incorrect");
            System.out.println("Press any key to try again");
            scn.nextLine();
            showLogin();
        }

        return true;

    }

    public static void showMain() {
        System.out.println("========== Main Page ==========");
        System.out.println("========== 26.11.2018 ==========");
        System.out.println("1. ");
        System.out.println("=== Main page ===");
        System.out.println("=== Main page ===");
    }


}

