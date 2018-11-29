package src;

import src.events.Event;
import src.users.Customer;
import src.users.Employee;
import src.users.Partner;

import java.util.ArrayList;

public class Database {

    private ArrayList<Event> events = FileManager.readEventsFile();
    private ArrayList<Employee> employees = FileManager.readEmployeesFile();
    private ArrayList<Partner> partners = FileManager.readPartnersFile();
    private ArrayList<Customer> customers = FileManager.readCustomersFile();

    // constructor
    public Database() {};


}
