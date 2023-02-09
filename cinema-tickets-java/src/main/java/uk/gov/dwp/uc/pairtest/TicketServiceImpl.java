package uk.gov.dwp.uc.pairtest;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import thirdparty.paymentgateway.TicketPaymentServiceImpl;
import thirdparty.seatbooking.SeatReservationServiceImpl;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest.Type;
import uk.gov.dwp.uc.pairtest.exception.InvalidAccountIdException;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

public class TicketServiceImpl implements TicketService {
    /**
     * Should only have private methods other than the one below.
     */
	private TicketPaymentServiceImpl ticketPaymentService = new TicketPaymentServiceImpl();
	private SeatReservationServiceImpl seatReservationService = new SeatReservationServiceImpl();

    @Override
    public void purchaseTickets(Long accountId, TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException {
    	if(accountId < 1) 
    		throw new InvalidAccountIdException("Invalid Account ID: " + accountId);
    	
    	int totalSeatsToAllocate = 0;
    	int totalAmountToPay = 0;
    	int totalAmountOfTickets = 0;
    	
    	boolean isAdultPresent = false;
    	
    	for(TicketTypeRequest tickets: ticketTypeRequests) {
    		totalAmountOfTickets += tickets.getNoOfTickets();
    		totalAmountToPay += tickets.getTotalPrice();
    		if(tickets.getTicketType() != Type.INFANT)
    			totalSeatsToAllocate += tickets.getNoOfTickets();
    		if(!isAdultPresent)
    			if(tickets.getTicketType() == Type.ADULT)
    				isAdultPresent = true;
    	}
    	
    	if(totalAmountOfTickets > 20)
    		throw new InvalidPurchaseException("Invalid request: only a maximum of 20 tickets can be purchased, current number is " + totalAmountOfTickets);
    	if(!isAdultPresent)
    		throw new InvalidPurchaseException("Invalid request: Atleast one adult required");
    	
    	seatReservationService.reserveSeat(accountId, totalSeatsToAllocate);
    	ticketPaymentService.makePayment(accountId, totalAmountToPay);
    	
    	System.out.println("totalSeatsToAllocate: " + totalSeatsToAllocate);
    	System.out.println("totalAmountToPay: " + totalAmountToPay);
    }

}
