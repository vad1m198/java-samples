package ru.vmerkotan;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Tests for TrackerRunner class.
* @author Vadim Merkotan
* @since  1.0
*/
public class TrackerRunnerTest {

	/**
	* test add Ticket Action.
	* When select Add then Ticket object should be
	* added to tracker
	*/
	@Test
	public void whenAddTicketThenShouldBeInTracker() {
		Input input = new StubInput(
			new String[] {"0", "name", "description", "y"}
		);
		Tracker tracker = new Tracker();
        MenuTracker menu = new MenuTracker(input, tracker);
        menu.fillActions();
		new TrackerRunner(input, menu).init();
		assertThat("Ticket should be added to Tracker", tracker.getAllTickets()[0].getName(), is("name"));
		assertThat("Ticket should be added to Tracker", tracker.getAllTickets().length, is(1));
	}

	/**
	* test edit Ticket Action.
	* When select Edit Item then passed Ticket name and description
	* should be updated
	*/
	@Test
	public void whenEditTicketThenFieldsShouldBeUpdated() {
		Tracker tracker = new Tracker();
		Ticket ticket = new Ticket("name", "description");
		tracker.addTicket(ticket);
		Input input = new StubInput(
			new String[] {"2", String.valueOf(ticket.getId()), "new name", "new description", "y"}
		);
        MenuTracker menu = new MenuTracker(input, tracker);
        menu.fillActions();
		new TrackerRunner(input, menu).init();
		assertThat("Ticket should be updated in Tracker", tracker.getAllTickets()[0].getName(), is("new name"));
		assertThat("Tickets length should not be changed", tracker.getAllTickets().length, is(1));
	}

	/**
	* test delete Ticket Action.
	* When select delete Item then passed Ticket should be deleted
	*/
	@Test
	public void whenDeleteTicketThenRemoveFromTracker() {
		Tracker tracker = new Tracker();
		Ticket ticket = new Ticket("name", "description");
		tracker.addTicket(ticket);
		Input input = new StubInput(
			new String[] {"3", String.valueOf(ticket.getId()), "3", "y"}
		);
        MenuTracker menu = new MenuTracker(input, tracker);
        menu.fillActions();
		new TrackerRunner(input, menu).init();
	}

	/**
	* test add comment Action.
	* When add comment then Ticket comments should be updated
	*/
	@Test
	public void whenAddCommentToTicketThenTicketCommentUpdated() {
		Tracker tracker = new Tracker();
		Ticket ticket = new Ticket("name", "description");
		tracker.addTicket(ticket);
		Input input = new StubInput(
			new String[] {"4", String.valueOf(ticket.getId()), "new comment", "y"}
		);
        MenuTracker menu = new MenuTracker(input, tracker);
        menu.fillActions();
		new TrackerRunner(input, menu).init();
		assertThat("Ticket comments length should be updated", tracker.getAllTickets()[0].getComments().length, is(1));
	}

	/**
	* verify Show all tickets.
	*/
	@Test
	public void verifyShowAllTickets() {
		Tracker tracker = new Tracker();
		Ticket ticket = new Ticket("name", "description");
		tracker.addTicket(ticket);
		Input input = new StubInput(
			new String[] {"1", "y"}
		);
        MenuTracker menu = new MenuTracker(input, tracker);
        menu.fillActions();
		new TrackerRunner(input, menu).init();
	}

	/**
	* verify filter items by name.
	*/
	@Test
	public void verifyFilterItemsByName() {
		Tracker tracker = new Tracker();
		Ticket ticket = new Ticket("name", "description");
		tracker.addTicket(ticket);
		Input input = new StubInput(
			new String[] {"5", "name", "y"}
		);
        MenuTracker menu = new MenuTracker(input, tracker);
        menu.fillActions();
		new TrackerRunner(input, menu).init();
	}

	/**
	* method to cover Show all tickets, filter items by name,
	* filter items by created date.
	*/
	@Test
	public void verifyFilterItemsByCreatedDate() {
		Tracker tracker = new Tracker();
		Ticket ticket = new Ticket("name", "description");
		tracker.addTicket(ticket);
		Input input = new StubInput(
			new String[] {"6", "456789", "y"}
		);
        MenuTracker menu = new MenuTracker(input, tracker);
        menu.fillActions();
		new TrackerRunner(input, menu).init();
	}
}