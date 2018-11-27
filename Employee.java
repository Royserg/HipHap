package com.company;

import java.util.ArrayList;

public class Employee {
    private int ID;
    private String name;
    private String password;
    private String email;
    private ArrayList<Integer> events;

    // Constructor
    public Employee(int id, String name, String pass, String email) {
        this.ID = id;
        this.name = name;
        this.password = pass;
        this.email = email;
        //TODO: add array of event IDs
    }

    // Getters

    /**
     * get Employee id
     * @return id (int) of Employee
     */
    public int getID() {
        return ID;
    }

    /**
     * get Employee name
     * @return name (String) of Employee
     */
    public String getName() {
        return name;
    }

    /**
     * get Employee password
     * @return password (String) of Employee
     */
    public String getPassword() {
        return password;
    }

    /**
     * get Employee email address
     * @return email (String) of Employee
     */
    public String getEmail() {
        return email;
    }
}

