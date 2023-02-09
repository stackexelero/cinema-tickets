package uk.gov.dwp.uc.pairtest.domain;

/**
 * Immutable Object
 */

public class TicketTypeRequest {

    private int noOfTickets;
    private Type type;

    public TicketTypeRequest(Type type, int noOfTickets) {
        this.type = type;
        this.noOfTickets = noOfTickets;
    }

    public int getNoOfTickets() {
        return noOfTickets;
    }

    public Type getTicketType() {
        return type;
    }

    public enum Type {
        ADULT, CHILD , INFANT
    }
    
    public int getTypePrice() {
        switch (type) {
            case ADULT: return 20;
            case CHILD: return 10;
            case INFANT: return 0;
        }
        return 0;
    }
    public int getTotalPrice() {
    	return noOfTickets * getTypePrice();
    }
}
