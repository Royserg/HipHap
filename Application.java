package src;

import src.users.Employee;

public interface Application {
    void addEvent();
    void editEvent(int eventID);
    void deleteEvent(int eventID);
    void logout();
    void login(Employee user);
    void showPartners();
    void showCustomers();
    void showEmployees();

}
