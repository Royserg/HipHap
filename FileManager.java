package com.company;

import com.company.events.Event;
import com.company.users.Customer;
import com.company.users.Employee;
import com.company.users.Partner;

import java.io.*;
import java.util.ArrayList;

public class FileManager {


    public static ArrayList<Event> readEventsFile() {
        ArrayList<Event> events = new ArrayList<>();

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        String IDsSplit = ";";

        // Event Read from .csv
        File csvFile = new File("./com/company/storage/events.csv");


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

                //System.out.println(ft.format(events.get(events.size()-1).startDate));
                //System.out.println(ft.format(events.get(events.size()-1).endDate));
                //System.out.println();
                // This checks the input, not the objects
//                System.out.println("Event - code = " + row[0] +
//                        ", ID = " + row[1] +
//                        ", name = " + row[2] +
//                        ", type = " + row[3] +
//                        ", start date = " + row[4] +
//                        ", end date = " + row[5]);



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

        return events;
    }


    public static ArrayList<Partner> readPartnersFile() {
        ArrayList<Partner> partners = new ArrayList<>();

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        String IDsSplit = ";";

        // Partners read from .csv
        File csvFile = new File("./com/company/storage/partners.csv");

        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] row = line.split(cvsSplitBy);

                // Partner(name,occupation)
                partners.add(new Partner(row[0], row[1]));


                //Checking if the read is correct -- the variables were public at checking for speed

//                System.out.print(partners.get(partners.size()-1).name);
//                System.out.print(" - ");
//                System.out.println(partners.get(partners.size()-1).occupation);


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

        return partners;
    }

    public static ArrayList<Employee> readEmployeesFile() {
        ArrayList<Employee> employees = new ArrayList<>();

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        String IDsSplit = ";";

        // Partners read from .csv
        File csvFile = new File("./com/company/storage/employees.csv");

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


                //Checking if the read is correct -- the variables were public at checking for speed

//                System.out.print(employees.get(employees.size()-1).ID);
//                System.out.print(" - ");
//                System.out.print(employees.get(employees.size()-1).eventIDs);
//                System.out.print(" - ");
//                System.out.print(employees.get(employees.size()-1).name);
//                System.out.print(" - ");
//                System.out.print(employees.get(employees.size()-1).password);
//                System.out.print(" - ");
//                System.out.println(employees.get(employees.size()-1).email);


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

        return employees;
    }

    public static ArrayList<Customer> readCustomersFile(){
        ArrayList<Customer> customers = new ArrayList<>();

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        String IDsSplit = ";";

        // Partners read from .csv
        File csvFile = new File("./com/company/storage/customers.csv");

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


                //Checking if the read is correct -- the variables were public at checking for speed

//                System.out.print(customers.get(customers.size()-1).ownEvents);
//                System.out.print(" - ");
//                System.out.println(customers.get(customers.size()-1).name);



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
        return customers;
    }
}
