package src;

import src.events.BusinessParty;
import src.events.Conference;
import src.events.Trip;
import src.events.Event;
import src.users.Customer;
import src.users.Employee;
import src.users.Manager;
import src.users.Partner;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;

public class Database {

    public ArrayList<Event> events = readEventsFile();
    public ArrayList<Employee> employees = readEmployeesFile();
    public ArrayList<Partner> partners = readPartnersFile();
    public ArrayList<Customer> customers = readCustomersFile();
    public Manager manager;

    // constructor
    public Database() {}


    /**
     * Searches through arrayList of Employees (database) for
     * a particular employee
     * @param name (String) - employee username to find
     * @return employee (Object)
     */
    public Employee getEmployeeByName(String name) {

        // loop over employees
        for (Employee employee: employees) {
            if (employee.getName().equals(name)) {
                return employee;
            }
        }

        // user not found
        return null;
    }

    public Employee getEmployeeByID(int ID) {

        // loop over employees
        for (Employee employee: employees) {
            if (employee.getID() == ID) {
                return employee;
            }
        }

        // user not found
        return null;
    }

    public ArrayList<Integer> getEmployeeEvents(ArrayList<Integer> eventIDs) {
        ArrayList<Integer> createdArray = new ArrayList<>();
        for (int i = 0; i < eventIDs.size(); i++) {
            // searching for matches of those IDs in the event array
            for (int j = 0; j < events.size(); j++) {
                if (eventIDs.get(i) == events.get(j).getID()) {
                    createdArray.add(events.get(j).getID());
                }
            }
        }
        return createdArray;
    }

    public ArrayList<Integer> getEmployeeEventsForToday(ArrayList<Integer> eventIDs) {
        ArrayList<Integer> createdArray = new ArrayList<>();
        for (int i = 0; i < eventIDs.size(); i++) {
            // searching for matches of those IDs in the event array
            for (int j = 0; j < events.size(); j++) {
                if (eventIDs.get(i) == events.get(j).getID())
                    if ((events.get(j).getOrgStartDate().before(new Date())) && (events.get(j).getOrgEndDate().after(new Date()))) {
                        createdArray.add(events.get(j).getID());
                    }

            }
        }
        return createdArray;
    }



    /**
     * Readers
     * Saves the data of the ArrayLists to the .csv files
     */
    public ArrayList<Event> readEventsFile() {
        ArrayList<Event> events = new ArrayList<>();

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        String IDsSplit = ";";

        // Event Read from .csv
        File csvFile = new File("src/storage/events.csv");

        try {
            br = new BufferedReader(new FileReader(csvFile));
            br.readLine();
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] row = line.split(cvsSplitBy);

                //  public Event(int ID, String eventType, String name, String serviceType, String startDate, String endDate, int nbOfHoursNeeded
                switch (row[1]) {
                    case "Conference":
                        events.add(new Conference(Integer.parseInt(row[0]), "Conference", row[2], row[3], row[4], row[5], Integer.parseInt(row[6]), row[7]));
                        break;

                    case "Trip":
                        events.add(new Trip(Integer.parseInt(row[0]), "Trip", row[2], row[3], row[4], row[5], Integer.parseInt(row[6]), row[7]));
                        break;

                    case "Business Party":
                        events.add(new BusinessParty(Integer.parseInt(row[0]), "Business party", row[2], row[3], row[4], row[5], Integer.parseInt(row[6]), row[7]));
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

    public ArrayList<Partner> readPartnersFile() {
        ArrayList<Partner> partners = new ArrayList<>();

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        String IDsSplit = ";";

        // Partners read from .csv
        File csvFile = new File("src/storage/partners.csv");

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

    public ArrayList<Employee> readEmployeesFile() {
        ArrayList<Employee> employees = new ArrayList<>();

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        String IDsSplit = ";";

        // Partners read from .csv
        File csvFile = new File("src/storage/employees.csv");

        try {
            br = new BufferedReader(new FileReader(csvFile));
            // skip first line of the file
            br.readLine();
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] row = line.split(cvsSplitBy);

                // Separating the IDs -- still a String
                String[] IDs = row[1].split(";");

                // Casting the String IDs to Integer
                ArrayList<Integer> castedIDs = new ArrayList<>();
                for (int i = 0; i < IDs.length; i++) {
                    if (IDs[i].equals("")) continue;
                    castedIDs.add(Integer.parseInt(IDs[i]));
                }

                if (Integer.parseInt(row[0]) == 1111) {
                    employees.add(new Manager(Integer.parseInt(row[0]), castedIDs, row[2],row[3],row[4], row[5]));
                }

                else {
                    // public Employee(ArrayList<Integer> ids, String name, String pass, String email, String avDate)
                    employees.add(new Employee(Integer.parseInt(row[0]), castedIDs, row[2],row[3],row[4], row[5]));
                }

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

    public ArrayList<Customer> readCustomersFile(){
        ArrayList<Customer> customers = new ArrayList<>();

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        String IDsSplit = ";";

        // Partners read from .csv
        File csvFile = new File("src/storage/customers.csv");

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

    /**
     * Writers
     * Saves the data of the ArrayLists to the .csv files
     */

    public void writeEventsFile(){
        PrintWriter pw = null;

        try {
            pw = new PrintWriter(new File("src/storage/events.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < events.size(); i++) {
            Event current = events.get(i);
            builder.append(current.getID()+",");
            builder.append(current.getEventType()+",");
            builder.append(current.getName()+",");
            builder.append(current.getServiceType()+",");
            builder.append(current.getOrgStartDate()+",");
            builder.append(current.getOrgEndDate()+",");
            builder.append('\n');
        }

        pw.write(builder.toString());
        pw.close();
    }

    public void writePartnersFile(){
        PrintWriter pw = null;

        try {
            pw = new PrintWriter(new File("src/storage/partners.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < partners.size(); i++) {
            Partner current = partners.get(i);
            builder.append(current.getName()+",");
            builder.append(current.getOccupation());
            builder.append('\n');
        }

        pw.write(builder.toString());
        pw.close();
    }

    public void writeEmployeesFile(){
        PrintWriter pw = null;

        try {
            pw = new PrintWriter(new File("src/storage/employees.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        StringBuilder builder = new StringBuilder();




        for (int i = 0; i < employees.size(); i++) {
            Employee current = employees.get(i);
            // Creating a string from the array of events, with ";" between them
            ArrayList<Integer> eventIDs = current.getEventIDs();
            String stringOfEventIDs = "";
            for (int j = 0; j < eventIDs.size(); j++) {
                stringOfEventIDs = stringOfEventIDs + eventIDs.get(j).toString();
            }

            builder.append(current.getID()+",");
            builder.append(stringOfEventIDs+",");
            builder.append(current.getName()+",");
            builder.append(current.getPassword()+",");
            builder.append(current.getEmail()+",");
            builder.append('\n');
        }

        pw.write(builder.toString());
        pw.close();
    }

    public void writeCustomersFile(){
        PrintWriter pw = null;

        try {
            pw = new PrintWriter(new File("src/storage/customers.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < customers.size(); i++) {
            Customer current = customers.get(i);
            // Creating a string from the array of events, with ";" between them
            ArrayList<Integer> eventIDs = current.getOwnEvents();
            String stringOfEventIDs = "";
            for (int j = 0; j < eventIDs.size(); j++) {
                stringOfEventIDs = stringOfEventIDs + eventIDs.get(j).toString();
            }
            builder.append(stringOfEventIDs + ",");
            builder.append(current.getName());
            builder.append('\n');
        }

        pw.write(builder.toString());
        pw.close();
    }

}
