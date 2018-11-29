package src.events;

public class Trip extends Event {
    private String transport;

    //Conference adds 1 constructor
    public Trip(int ID, String name, String type, String startDate, String endDate, String transport) {
        super(ID, name, type, startDate, endDate);
        this.transport = transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public String getTransport() {
        return this.transport;
    }
}