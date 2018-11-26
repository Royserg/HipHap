package com.company;

import java.util.ArrayList;

public class Employee {
    ArrayList<Integer> IDs = new ArrayList<Integer>();
    String name;
    String password;
    String email;

    // Constructor
    Employee() {
    }

    // Set-ers
    public void addID(int ID) {
        IDs.add(ID);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Get-ers
    public ArrayList<Integer> getID(){
        return IDs;
    }

    public String getName(){
        return this.name;
    }

    public String getPassword(){
        return this.password;
    }

    public String getEmail(){
        return this.email;
    }

}
