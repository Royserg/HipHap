package com.company;

public class Employee {
    int ID;
    String name;
    String password;
    String email;

    // Constructor
    Employee() {
    }

    // Set-ers
    public void setID(int ID) {
        this.ID = ID;
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
    public int getID(){
        return this.ID;
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
