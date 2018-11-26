package com.company;

public class Manager extends Employee {
    private int ID;
    private String name;
    private String password;
    private String email;

    public Manager(int id, String name, String pass, String email) {
        super(id, name, pass, email);
    }
}
