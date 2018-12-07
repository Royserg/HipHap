package src.users;

import java.util.ArrayList;

public class Manager extends Employee {

    /**
     * Manager constructor, uses employee constructor as a base*/
    public Manager(int id, ArrayList<Integer> ids, String name, String pass, String email, String avDate) {
        super(id, ids, name, pass, email, avDate);
    }
}