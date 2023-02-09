package uk.gov.dwp.uc.pairtest;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest.Type;

class TicketServiceTest {

	@Test
	void testMaxNumberOfTicketsFail() {
		TicketServiceImpl service = new TicketServiceImpl();
		TicketTypeRequest[] array = new TicketTypeRequest[3];
		array[0] = new TicketTypeRequest(Type.ADULT, 10);
		array[1] = new TicketTypeRequest(Type.CHILD, 10);
		array[2] = new TicketTypeRequest(Type.INFANT, 10);
		service.purchaseTickets(Long.valueOf(235), array);
	}
	@Test
	void testMaxNumberOfTicketsSuccess() {
		TicketServiceImpl service = new TicketServiceImpl();
		TicketTypeRequest[] array = new TicketTypeRequest[4];
		array[0] = new TicketTypeRequest(Type.ADULT, 2);
		array[1] = new TicketTypeRequest(Type.ADULT, 3);
		array[2] = new TicketTypeRequest(Type.CHILD, 5);
		array[3] = new TicketTypeRequest(Type.INFANT, 10);
		service.purchaseTickets(Long.valueOf(235), array);
	}
	
	@Test
	void testTicketsWithoutAdultFail() {
		TicketServiceImpl service = new TicketServiceImpl();
		TicketTypeRequest[] array = new TicketTypeRequest[2];
		array[0] = new TicketTypeRequest(Type.CHILD, 10);
		array[1] = new TicketTypeRequest(Type.INFANT, 10);
		service.purchaseTickets(Long.valueOf(235), array);
	}
	@Test
	void testTicketsWithAdultSuccess() {
		TicketServiceImpl service = new TicketServiceImpl();
		TicketTypeRequest[] array = new TicketTypeRequest[3];
		array[0] = new TicketTypeRequest(Type.ADULT, 1);
		array[1] = new TicketTypeRequest(Type.CHILD, 5);
		array[2] = new TicketTypeRequest(Type.INFANT, 5);
		service.purchaseTickets(Long.valueOf(235), array);
	}
	
	@Test
	void testAccountIdFail() {
		TicketServiceImpl service = new TicketServiceImpl();
		TicketTypeRequest[] array = new TicketTypeRequest[2];
		array[0] = new TicketTypeRequest(Type.ADULT, 1);
		array[1] = new TicketTypeRequest(Type.CHILD, 1);
		service.purchaseTickets(Long.valueOf(0), array);
	}
	@Test
	void testAccountIdSuccess() {
		TicketServiceImpl service = new TicketServiceImpl();
		TicketTypeRequest[] array = new TicketTypeRequest[2];
		array[0] = new TicketTypeRequest(Type.ADULT, 1);
		array[1] = new TicketTypeRequest(Type.CHILD, 1);
		service.purchaseTickets(Long.valueOf(1), array);
	}
	
}
