package src.users;

import java.util.ArrayList;

public class Manager extends Employee {

    private boolean specialAccess = true;
    /**
     * Manager constructor, uses employee constructor as a base
     * @param id (int) id of the object
     * @param ids (ArrayList[Integer]) list of all event id associated with the object
     * @param name (String) name of the user in the system
     * @param pass (String) password for the account
     * @param email (String) address email
     * @param avDate (String) String representation of a first available date for working on a next project
     */
    public Manager(int id, ArrayList<Integer> ids, String name, String pass, String email, String avDate) {
        super(id, ids, name, pass, email, avDate);
    }
}