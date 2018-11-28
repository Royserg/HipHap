package com.company;

import java.io.*;
import java.util.ArrayList;
//imports for csv


public class Main {

    static ArrayList<Event> events = FileManager.readEventsFile();
    static ArrayList<Employee> employees = FileManager.readEmployeesFile();
    static ArrayList<Partner> partners = FileManager.readPartnersFile();
    static ArrayList<Customer> customers = FileManager.readCustomersFile();

    //Screen screen = new Screen(); dont we have to create an instance because of username and pass fields? and then call showLogin() on the instance

    public static void main(String[] args) {

        Screen.showLogin();
        if (validateLogin()) {
            //everything after login
        } else {
            System.out.print("Incorrect username or password. Try again.");
            Screen.showLogin(); //login again
        }

        initiateData();//this goes inside if? or before login screen? I think it would be better to put it before login -Aneja
    }

    /*
     * validates Login information to see if the user exists and if he entered the correct password
     * */
    private static boolean validateLogin() {
        boolean validation = false;

        int i = 0;
        boolean found = false;

        while (!found) {
            if (employees.get(i).getName().equals(Screen.getUsername()))
                found = true;
            else i++;
        }

        if (employees.get(i).getPassword().equals(Screen.getPassword()))
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
