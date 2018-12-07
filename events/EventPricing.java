package src.events;

public interface EventPricing {

    /**
     * Method to implement in each event type, should calculate total event price that customer needs to pay.*/
    void calculateEventPrices();

}