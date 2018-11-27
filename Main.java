package com.company;

import java.util.ArrayList;
//imports for csv
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    static ArrayList<Event> events = new ArrayList<Event>();
    static ArrayList<Employee> employees = new ArrayList<Employee>();
    static ArrayList<Partner> partners = new ArrayList<Partner>();
    static ArrayList<Customer> customers = new ArrayList<Customer>();

    public static void main(String[] args) {

    // show login screen
    Screen.showLogin();


    initiateData();

        // show login screen
        Screen.showLogin();
    }

    private static void initiateData() {
        String csvFile = null;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        String IDsSplit = ";";
        // Event Read from .csv
        csvFile = "C:\\Users\\Admin\\Desktop\\events.csv";


        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] row = line.split(cvsSplitBy);

                // Event(ID,name,type)
                switch(row[0]) {
                    case "1":
                        events.add(new Event(Integer.parseInt(row[1]), row[2], row[3], row[4], row[5]));
                        break;
                    default:
                        events.add(new Event(Integer.parseInt(row[1]), row[2], row[3], row[4], row[5]));
                        break;
                }


                System.out.println("Event name: " + row[2] + " , type = " + row[3]);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
