package ru.vmerkotan;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
* Tests for Tracker class
* @author Vadim Merkotan
* @since  1.0
*/
public class TrackerTest {
	
	/**
	* Test add Ticket to tracker
	* Only added Tickets should be returned by getAllTickets method
	*/
	@Test
	public void whenAddTicketsThenEqualArrayReturned() {
		Tracker tracker = new Tracker();
		String ticketName = "Ticket name";
		String ticketDescription = "Ticket description";		
		int ticketsNumber = 11;
		Ticket[] expectedArray = new Ticket[ticketsNumber];
		for(int i = 0; i < ticketsNumber; i++) {
			Ticket ticket = new Ticket(ticketName, ticketDescription);
			tracker.addTicket(ticket);
			expectedArray[i] = ticket;
		}		
		assertThat("Return All added tickets without null", tracker.getAllTickets(), is(expectedArray) );
	}
	
	/**
	* Test getTicketById method
	* Return Ticket object with Id equal to passed value
	*/
	@Test
	public void whenGetByIdThenExpectedTicketReturned() {
		Tracker tracker = new Tracker();
		String ticketName = "Ticket name";
		String ticketDescription = "Ticket description";
		Ticket firstTicket = new Ticket(ticketName, ticketDescription);
		Ticket secondTicket = new Ticket(ticketName, ticketDescription);
		tracker.addTicket(firstTicket);
		tracker.addTicket(secondTicket);

		assertThat("Ticket with passed value should be returned", tracker.getTicketById(firstTicket.getId()), is(firstTicket) );
	}
	
	/**
	* Test getTicketById method
	* Return null if Ticket with passed value not exist
	*/
	@Test
	public void whenNotExistThenNull() {
		Tracker tracker = new Tracker();
		String ticketName = "Ticket name";
		String ticketDescription = "Ticket description";
		Ticket firstTicket = new Ticket(ticketName, ticketDescription);
		Ticket secondTicket = new Ticket(ticketName, ticketDescription);
		tracker.addTicket(firstTicket);
		tracker.addTicket(secondTicket);

		assertNull("Return null if Ticket with Id do not exsits", tracker.getTicketById(1L) );
	}
	
	/**
	* Test deleteById method
	* should remove Ticket with passed id from tracker
	*/
	@Test
	public void whenDeleteByIdThenRemoveTicket() {
		Tracker tracker = new Tracker();
		String ticketName = "Ticket name";
		String ticketDescription = "Ticket description";
		Ticket firstTicket = new Ticket(ticketName, ticketDescription);
		Ticket secondTicket = new Ticket(ticketName, ticketDescription);
		tracker.addTicket(firstTicket);
		tracker.addTicket(secondTicket);
		tracker.deleteTicketById(firstTicket.getId());
		Ticket[] expectedTickets = {secondTicket};

		assertThat("Ticket with passed value should be removed", tracker.getAllTickets(), is(expectedTickets) );
	}
	
	/**
	* Test deleteById method
	* should do nothing if passed fake id
	*/
	@Test
	public void whenDeleteByFakeIdThenNothingRemove() {
		Tracker tracker = new Tracker();
		String ticketName = "Ticket name";
		String ticketDescription = "Ticket description";
		Ticket firstTicket = new Ticket(ticketName, ticketDescription);
		Ticket secondTicket = new Ticket(ticketName, ticketDescription);
		tracker.addTicket(firstTicket);
		tracker.addTicket(secondTicket);
		tracker.deleteTicketById(10L);
		Ticket[] expectedTickets = {firstTicket, secondTicket};

		assertThat("Nothing should be removed if pass fake Id", tracker.getAllTickets(), is(expectedTickets) );
	}
	
	/**
	* Test addCommentToTicket method
	* should add comment to appropriate Ticket object
	*/
	@Test
	public void whenAddCommentToTicketThenGetCommentIncrease() {
		Tracker tracker = new Tracker();
		String ticketName = "Ticket name";
		String ticketDescription = "Ticket description";
		String ticketComment = "Ticket comment";
		Ticket firstTicket = new Ticket(ticketName, ticketDescription);
		Ticket secondTicket = new Ticket(ticketName, ticketDescription);
		tracker.addTicket(firstTicket);
		tracker.addTicket(secondTicket);
		tracker.addCommentToTicket(firstTicket.getId(), ticketComment);		
		String[] expectedComments = {ticketComment};

		assertThat("Comments should be added to proper Ticket", tracker.getTicketById(firstTicket.getId()).getComments(), is(expectedComments) );
		assertThat("Comments should not be added another Ticket", tracker.getTicketById(secondTicket.getId()).getComments(), is(new String[0]) );
	}
	
	/**
	* Test updateTicket method
	* should update Ticket object with passed name and description
	*/
	@Test
	public void whenUpdateTicketThenProperTicketShopuldBeUpdated() {
		Tracker tracker = new Tracker();
		String ticketName = "Ticket name";
		String ticketDescription = "Ticket description";
		String ticketNewName = "Ticket new name";
		String ticketNewDescription = "Ticket new description";
		
		Ticket firstTicket = new Ticket(ticketName, ticketDescription);
		Ticket secondTicket = new Ticket(ticketName, ticketDescription);
		tracker.addTicket(firstTicket);
		tracker.addTicket(secondTicket);		
		Ticket updatedTicket = new Ticket(ticketNewName, ticketNewDescription);
		tracker.updateTicket(firstTicket.getId(), updatedTicket);
		
		assertThat("name field should be updated in proper Ticket", tracker.getTicketById(firstTicket.getId()).getName(), is(ticketNewName) );
		assertThat("description field should be updated in proper Ticket", tracker.getTicketById(firstTicket.getId()).getDescription(), is(ticketNewDescription) );
		
		assertThat("name field should not be updated in not apropriate Ticket", tracker.getTicketById(secondTicket.getId()).getName(), is(ticketName) );
		assertThat("description field should not be updated in not apropriate Ticket", tracker.getTicketById(secondTicket.getId()).getDescription(), is(ticketDescription) );		
	}
	
	/**
	* Test filterTicketsByName method
	* should return Ticket[] with Ticket objects
	* where name field contains passed String value
	*/
	@Test
	public void whenFilterByNameThenReturnContainsNames() {
		Tracker tracker = new Tracker();		
		
		Ticket firstTicket = new Ticket("first name", "");
		Ticket secondTicket = new Ticket("second name", "");
		Ticket thirdTicket = new Ticket("third", "");
		tracker.addTicket(firstTicket);
		tracker.addTicket(secondTicket);
		tracker.addTicket(thirdTicket);		

		Ticket[] expectedTickets = {firstTicket, secondTicket};		
		
		assertThat("filterTicketsByName return object if name field contains passed value", tracker.filterTicketsByName("Name"), is(expectedTickets) );		
	}
	
	/**
	* Test filterTicketsByName method
	* should return Ticket[0] if pass null value
	*/
	@Test
	public void whenPassNullThenReturnEmptyArr() {
		Tracker tracker = new Tracker();		
		
		Ticket firstTicket = new Ticket("first name", "");
		Ticket secondTicket = new Ticket("second name", "");
		Ticket thirdTicket = new Ticket("third", "");
		tracker.addTicket(firstTicket);
		tracker.addTicket(secondTicket);
		tracker.addTicket(thirdTicket);
		
		assertThat("filterTicketsByName return empty array if pass null", tracker.filterTicketsByName("null"), is(new Ticket[0]) );		
	}
	
	/**
	* Test filterTicketsCreatedAfter method
	* should return Ticket[] with Ticket obejcts
	* where createdDate is greated then passed
	*/
	@Test
	public void whenFilterCreatedAfterThenReturnCreatedDatesAfter() throws InterruptedException {
		Tracker tracker = new Tracker();		
		
		Ticket firstTicket = new Ticket("first name", "");
		Thread.sleep(1000);
		long testCreatedDate = System.currentTimeMillis();
		Ticket secondTicket = new Ticket("second name", "");
		Ticket thirdTicket = new Ticket("third", "");
		tracker.addTicket(firstTicket);
		tracker.addTicket(secondTicket);
		tracker.addTicket(thirdTicket);
		
		Ticket[] expectedTickets = {secondTicket, thirdTicket};
		assertThat("return Ticket objects where createdDate is greater then passed value", tracker.filterTicketsCreatedAfter(testCreatedDate), is(expectedTickets) );		
	}
}