package src;

import src.events.Event;
import src.users.Customer;
import src.users.Employee;
import src.users.Partner;

import java.util.ArrayList;

public class Database {

    public ArrayList<Event> events = FileManager.readEventsFile();
    public ArrayList<Employee> employees = FileManager.readEmployeesFile();
    public ArrayList<Partner> partners = FileManager.readPartnersFile();
    public ArrayList<Customer> customers = FileManager.readCustomersFile();

    // constructor
    public Database() {};


}
