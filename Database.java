package src;

import src.events.BusinessParty;
import src.events.Conference;
import src.events.Trip;
import src.events.Event;
import src.users.Customer;
import src.users.Employee;
import src.users.Manager;
import src.users.Partner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;

public class Database {

    ArrayList<Employee> employees = readEmployeesFile();
    ArrayList<Event> events = readEventsFile();
    ArrayList<Partner> partners = readPartnersFile();
    ArrayList<Customer> customers = readCustomersFile();

    // constructor
    Database() {}

    /**
     * Searches through arrayList of Employees (database) for a particular employee based on name
     * @param name (String) - employee username to find
     * @return employee (Employee)
     */
    Employee getEmployeeByName(String name) {

        // loop over employees
        for (Employee employee: employees) {
            if (employee.getName().equals(name)) {
                return employee;
            }
        }

        // user not found
        return null;
    }

    /**
     * Searches through arrayList of Employees (database) for a particular employee based on ID
     * @param ID (int) - employee ID to find
     * @return employee (Employee)
     */
    Employee getEmployeeByID(int ID) {

        // loop over employees
        for (Employee employee: employees) {
            if (employee.getID() == ID) {
                return employee;
            }
        }

        // user not found
        return null;
    }

    /**
     * Searches through arrayList of Events(database) for a particular event based on ID
     * @param ID (int) - event ID to find
     * @return event (Event)
     */
    Event getEventByID(int ID){
        for(Event event: events){
            if(event.getID() == ID){
                return event;
            }
        }
        return null;
    }

    /**
     * Searches through arrayList of Partners(database) for a particular partner based on ID
     * @param ID (int) - partner ID to find
     * @return partner (Partner)
     */
    Partner getPartnerByID(int ID){
        for(Partner partner: partners){
            if(partner.getID() == ID)
                return partner;
        }
        return null;
    }

    /**
     * Gets employee's events. Events that employee is responsible for.
     * @param eventIDs (ArrayList<Integer>) - all event's IDs
     * @return employeesEvents (ArrayList<Integer>) - employee's event's IDs*/
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

    /**
     * Gets Employee's events for today
     * @param  eventIDs (ArrayList<Integer>) - event IDs
     * @return todaysEvents (ArrayList<Integer>) - arrayList of today's events*/
    ArrayList<Integer> getEmployeeEventsForToday(ArrayList<Integer> eventIDs) {
        // day start => Today 00:00:00
        Date dayStart = new Date();
        dayStart.setHours(0); dayStart.setMinutes(0); dayStart.setSeconds(0);
        // day End => Today 23:59:59
        Date dayEnd = new Date();
        dayEnd.setHours(23); dayEnd.setMinutes(59); dayEnd.setSeconds(59);

        ArrayList<Integer> createdArray = new ArrayList<>();
        for (int i = 0; i < eventIDs.size(); i++) {
            // searching for matches of those IDs in the event array
            for (int j = 0; j < events.size(); j++) {
                if (eventIDs.get(i) == events.get(j).getID())
                    if ((events.get(j).getOrgStartDate().before(dayEnd)) && (events.get(j).getOrgEndDate().after(dayStart))) {
                        createdArray.add(events.get(j).getID());
                    }
            }
        }
        return createdArray;
    }

    /**
     * get list (ArrayList) of events for the provided date
     * @param date (Date)
     * @return (ArrayList) of events
     */
    ArrayList<Event> getEvents(Date date, int id) {
        // set endDate to 23:59:59 same day
        Date endDate = new Date(date.getTime());

        return getEvents(date, endDate, id);
    }

    /**
     * Gets events for a certain period of time
     * @param startDate (Date) - start date of the period
     * @param endDate (Date) - end date of the period
     * @param id (int) - id of employee/manager requesting to get events for a period
     * @return events (ArrayList<Event>) - ArrayList of events for a certain period of time*/
    ArrayList<Event> getEvents(Date startDate, Date endDate, int id) throws ClassCastException{
        endDate.setHours(23);
        endDate.setMinutes(59);
        endDate.setSeconds(59);

        // arrayList for gathered events
        ArrayList<Event> foundEvents = new ArrayList<>();

        // manager fetches everyone's events
        if (id == 1111) {
            for (Event event : events) {
                if (event.getOrgStartDate().before(endDate) && event.getOrgEndDate().after(startDate) ) {
                    foundEvents.add(event);
                }
            }
        } else {
            // fetch events for particular user
            for (Event event : events) {
                if (id == event.employeeResponsible.getID() && event.getOrgStartDate().before(endDate) && event.getOrgEndDate().after(startDate) ) {
                    foundEvents.add(event);
                }
            }
        }

        return foundEvents;
    }



    /**
     * Reads event.csv file and converts it to an arrayList
     * @return events (ArrayList<Event>) - arrayList of all events*/
    ArrayList<Event> readEventsFile() {
        ArrayList<Event> events = new ArrayList<>();

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        String IDsSplit = ";";

        // Event Read from .csv
        File csvFile = new File("src/storage/events.csv");

        try {
            br = new BufferedReader(new FileReader(csvFile));
            // skip 1st title line
            br.readLine();
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] row = line.split(cvsSplitBy);

                switch (row[1]) {
                    case "Conference":
                        events.add(new Conference(Integer.parseInt(row[0]), "Conference", row[2], row[3], getEmployeeByID(Integer.parseInt(row[4])), row[5], row[6], row[7], Integer.parseInt(row[8]), row[9], row[10]));
                        break;

                    case "Trip":
                        events.add(new Trip(Integer.parseInt(row[0]), "Trip", row[2], row[3], getEmployeeByID(Integer.parseInt(row[4])), row[5], row[6], row[7], Integer.parseInt(row[8]), row[9], row[10]));
                        break;

                    case "Business Party":
                        events.add(new BusinessParty(Integer.parseInt(row[0]), "Business party", row[2], row[3], getEmployeeByID(Integer.parseInt(row[4])), row[5], row[6], row[7], Integer.parseInt(row[8]), row[9], row[10]));

                        break;
                }
            }

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

    /**
     * Reads partner.csv file and converts it into arrayList
     * @return partners (ArrayList<Partner>) - arrayList of all partners*/
    private ArrayList<Partner> readPartnersFile() {
        ArrayList<Partner> partners = new ArrayList<>();

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        String IDsSplit = ";";

        // Partners read from .csv
        File csvFile = new File("src/storage/partners.csv");

        try {
            br = new BufferedReader(new FileReader(csvFile));
            // skip first tile line
            br.readLine();
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] row = line.split(cvsSplitBy);

                //  public Partner(int ID, String name, String occupation, String address, String bookedDates)
                partners.add(new Partner( Integer.parseInt(row[0]), row[1], row[2], row[3], row[4]));
            }

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

    /**
     * Reads employees.csv file and converts it into arrayList
     * @return employees (ArrayList<Employee>) - arrayList of all employees*/
    private ArrayList<Employee> readEmployeesFile() {
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
                    employees.add(new Manager(Integer.parseInt(row[0]), castedIDs, row[2], row[3], row[4], row[5]));
                }

                else {
                    // public Employee(ArrayList<Integer> ids, String name, String pass, String email, String avDate)
                    employees.add(new Employee(Integer.parseInt(row[0]), castedIDs, row[2], row[3], row[4], row[5]));
                }

            }

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

    /**
     * Reads customers files and converts it into arraylist
     * @return customers (ArrayList<Customer>) - arrayList of all customers*/
    private ArrayList<Customer> readCustomersFile(){
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
            }

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
     * Writes data to events.csv file from arrayList*/
    void writeEventsFile(){
        PrintWriter pw = null;

        try {
            pw = new PrintWriter(new File("src/storage/events.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        StringBuilder builder = new StringBuilder();

        pw.write("event_Id,event_type,name,service_type,employee_id,org_start_date,org_end_date, actualstartdate, hours needed, specs, partner IDs\n");

        for (int i = 0; i < events.size(); i++) {
            Event current = events.get(i);
            builder.append(current.getID()+",");
            builder.append(current.getEventType()+",");
            builder.append(current.getName()+",");
            builder.append(current.getServiceType()+",");
            builder.append(current.getEmployee().getID()+",");
            builder.append(new SimpleDateFormat("dd.MM.yyyy 'at' HH").format(current.getOrgStartDate())+",");
            builder.append(new SimpleDateFormat("dd.MM.yyyy 'at' HH").format(current.getOrgEndDate())+",");
            builder.append(new SimpleDateFormat("dd.MM.yyyy 'at' HH").format(current.getStartOfEvent()) + ",");
            builder.append(current.getNbOfHoursNeeded()+",");
            builder.append(current.getSpecs()+",");
            builder.append(current.savePartnerIDs());
            builder.append('\n');
        }

        pw.write(builder.toString());
        pw.close();
    }

    /**
     * Writes data to partners.csv file from partners arrayList*/
    void writePartnersFile(){
        PrintWriter pw = null;

        try {
            pw = new PrintWriter(new File("src/storage/partners.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        StringBuilder builder = new StringBuilder();

        pw.write("id,name,service type, address, booked dates\n");

        for (int i = 0; i < partners.size(); i++) {
            Partner current = partners.get(i);
            builder.append(current.getID() + ",");
            builder.append(current.getName()+",");
            builder.append(current.getOccupation()+",");
            builder.append(current.getAddress()+",");
            builder.append(current.getBookedDates()+",");
            builder.append('\n');
        }

        pw.write(builder.toString());
        pw.close();
    }

    /**
     * Writes data to employees.csv file from arrayList*/
    void writeEmployeesFile(){
        PrintWriter pw = null;

        try {
            pw = new PrintWriter(new File("src/storage/employees.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        StringBuilder builder = new StringBuilder();

        pw.write("id,event_IDs,name,password,email,last_event_date\n");


        for (int i = 0; i < employees.size(); i++) {
            Employee current = employees.get(i);
            // Creating a string from the array of events, with ";" between them
            ArrayList<Integer> eventIDs = current.getEventIDs();
            String stringOfEventIDs = "";

            if (!eventIDs.isEmpty()) {
                // this builds the string of events with ; between them except for the last one
                for (int j = 0; j < eventIDs.size() - 1; j++) {
                    stringOfEventIDs = stringOfEventIDs + eventIDs.get(j).toString() + ";";
                }

                // adding the last event in the string without the ;
                stringOfEventIDs = stringOfEventIDs + eventIDs.get(eventIDs.size()-1).toString();
            }


            builder.append(current.getID()+",");
            builder.append(stringOfEventIDs+",");
            builder.append(current.getName()+",");
            builder.append(current.getPassword()+",");
            builder.append(current.getEmail()+",");
            builder.append(new SimpleDateFormat("dd.MM.yyyy 'at' HH").format(current.getLastEventInfo())+",");
            builder.append('\n');
        }

        pw.write(builder.toString());
        pw.close();
    }

    /**
     * Writes data to customers.csv from arrayList*/
    void writeCustomersFile(){
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
            // this builds the string of events with ; between them except for the last one
            for (int j = 0; j < eventIDs.size()-1; j++) {
                stringOfEventIDs = stringOfEventIDs + eventIDs.get(j).toString() + ";";
            }
            // adding the last event in the string without the ;
            stringOfEventIDs = stringOfEventIDs + eventIDs.get(eventIDs.size()-1).toString();
            builder.append(stringOfEventIDs + ",");
            builder.append(current.getName());
            builder.append('\n');
        }

        pw.write(builder.toString());
        pw.close();
    }

}
