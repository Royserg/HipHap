package com.company;

public class Conference extends Event {
    String officeSupplies; //adding 1 attribute

    //businessparty adding 1 constructor
        Conference(int ID,
                  String name,
                  String type,
                  String startDate,
                  String endDate,
                  String officeSupplies) {
        super(ID, name, type, startDate, endDate);
        this.officeSupplies = officeSupplies;
    }

    public void setofficeSupplies(String officeSupplies) {
        this.officeSupplies = officeSupplies;
    }

    public String getOfficeSupplies() {
        return this.officeSupplies;
    }
}