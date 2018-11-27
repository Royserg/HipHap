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
    }

    private static void initiateData() {
        readEventsFile();
        readPartnersFile();
        readEmployeesFile();
        readCustomersFile();

    }

    public static void readEventsFile() {
        String csvFile = null;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        String IDsSplit = ";";
        // Event Read from .csv
        csvFile = "X:\\Projects\\IntelliJ\\HipHap\\files\\events.csv";


        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] row = line.split(cvsSplitBy);

                // Event(ID,name,type,startDate,endDate)
                //TODO: make switch cases for the different type of events
                switch (row[0]) {
                    case "1":
                        events.add(new Event(Integer.parseInt(row[1]), row[2], row[3], row[4], row[5]));
                        break;
                    default:
                        events.add(new Event(Integer.parseInt(row[1]), row[2], row[3], row[4], row[5]));
                        break;
                }

                /*
                // This checks the input, not the objects
                System.out.println("Event - code = " + row[0] +
                        ", ID = " + row[1] +
                        ", name = " + row[2] +
                        ", type = " + row[3] +
                        ", start date = " + row[4] +
                        ", end date = " + row[5]);

                */

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
        } //end of try-catch-finally
    }


    public static void readPartnersFile() {
        String csvFile = null;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        String IDsSplit = ";";
        // Event Read from .csv
        csvFile = "X:\\Projects\\IntelliJ\\HipHap\\files\\partners.csv";

        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] row = line.split(cvsSplitBy);

                // Partner(name,occupation)
                partners.add(new Partner(row[0], row[1]));

                /*
                //Checking if the read is correct -- the variables were public at checking for speed

                System.out.print(partners.get(partners.size()-1).name);
                System.out.print(" - ");
                System.out.println(partners.get(partners.size()-1).occupation);

                */
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

    public static void readEmployeesFile() {
        String csvFile = null;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        String IDsSplit = ";";
        // Event Read from .csv
        csvFile = "X:\\Projects\\IntelliJ\\HipHap\\files\\employees.csv";

        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] row = line.split(cvsSplitBy);

                // Separating the IDs -- still a String
                String[] IDs = row[1].split(";");

                // Casting the String IDs to Integer
                ArrayList<Integer> castedIDs = new ArrayList<>();
                for (int i = 0; i < IDs.length; i++) {
                    castedIDs.add(Integer.parseInt(IDs[i]));
                }

                // public Employee(ArrayList<Integer> ids, String name, String pass, String email)
                employees.add(new Employee(Integer.parseInt(row[0]), castedIDs, row[2],row[3],row[4]));

                /*
                //Checking if the read is correct -- the variables were public at checking for speed

                System.out.print(employees.get(employees.size()-1).ID);
                System.out.print(" - ");
                System.out.print(employees.get(employees.size()-1).eventIDs);
                System.out.print(" - ");
                System.out.print(employees.get(employees.size()-1).name);
                System.out.print(" - ");
                System.out.print(employees.get(employees.size()-1).password);
                System.out.print(" - ");
                System.out.println(employees.get(employees.size()-1).email);

                */
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

    public static void readCustomersFile(){
        String csvFile = null;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        String IDsSplit = ";";
        // Event Read from .csv
        csvFile = "X:\\Projects\\IntelliJ\\HipHap\\files\\customers.csv";

        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] row = line.split(cvsSplitBy);

                // Separating the IDs -- still a String
                String[] IDs = row[0].split(";");

                // Casting the String IDs to Integer
                ArrayList<Integer> castedIDs = new ArrayList<>();
                for (int i = 0; i < IDs.length; i++) {
                    castedIDs.add(Integer.parseInt(IDs[i]));
                }

                // public Employee(ArrayList<Integer> ids, String name, String pass, String email)
                customers.add(new Customer(castedIDs, row[1]));

                /*
                //Checking if the read is correct -- the variables were public at checking for speed

                System.out.print(customers.get(customers.size()-1).ownEvents);
                System.out.print(" - ");
                System.out.println(customers.get(customers.size()-1).name);

                */

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
