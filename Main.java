package com.company;

import java.util.ArrayList;
//imports for csv


public class Main {

    static ArrayList<Event> events = FileManager.readEventsFile();
    static ArrayList<Employee> employees = FileManager.readEmployeesFile();
    static ArrayList<Partner> partners = FileManager.readPartnersFile();
    static ArrayList<Customer> customers = FileManager.readCustomersFile();

    public static void main(String[] args) {

        boolean loggedIn = Screen.showLogin();
        if (loggedIn) {
            //everything after login
        }

        initiateData();
    }

    /*
     * validates Login information to see if the user exists and if he entered the correct password
     * */
    public static boolean validateLogin(String username, String password) {
        boolean validation = false;
        int i = 0;
        boolean found = false;

        for (i = 0; i < employees.size(); i++) {
            if (employees.get(i).getName().equals(username)) {
                found = true;
                break;
            }
        }

        if (employees.get(i-1).getPassword().equals(password))
            validation = true;

        return validation;
    }

    private static void initiateData() {
//        readEventsFile();
//        readPartnersFile();
//        readEmployeesFile();
//        readCustomersFile();

    }
}
