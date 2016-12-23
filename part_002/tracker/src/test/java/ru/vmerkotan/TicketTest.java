package ru.vmerkotan;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
* Tests for Ticket class.
* @author Vadim Merkotans
* @since  1.0
* @version $Id$
*/
public class TicketTest {

	/**
	* Test create Ticket object.
	* Crated Ticket object should have name, Id, description, createdDate
	*/
	@Test
	public void whenCreateTicketThenAllFiledsPopulated() {
		String ticketName = "Ticket name";
		String ticketDescription = "Ticket description";
		Ticket ticket = new Ticket(ticketName, ticketDescription);
		assertEquals(ticketName, ticket.getName());
		assertEquals(ticketDescription, ticket.getDescription());
		assertTrue("Ticket Id should be populated", ticket.getId() > 0L);
		assertTrue("Ticket createdDate should be populated", ticket.getCreatedDate() > 0L);
	}

	/**
	* Test setName method.
	* setName should update name field value
	*/
	@Test
	public void whenSetNameThenNameFieldChanged() {
		String ticketName = "Ticket name";
		String ticketNewName = "Ticket new name";
		String ticketDescription = "Ticket description";
		Ticket ticket = new Ticket(ticketName, ticketDescription);
		ticket.setName(ticketNewName);
		assertThat("Name field value should be chaged to new one", ticket.getName(), is(ticketNewName));
	}

	/**
	* Test setDescription method.
	* setDescription should update description field value
	*/
	@Test
	public void whenSetDescriptionThenDescriptionFieldChanged() {
		String ticketName = "Ticket name";
		String ticketDescription = "Ticket description";
		String ticketNewDescription = "Ticket new description";
		Ticket ticket = new Ticket(ticketName, ticketDescription);
		ticket.setDescription(ticketNewDescription);
		assertThat("Name field value should be chaged to new one", ticket.getDescription(), is(ticketNewDescription));
	}

	/**
	* Test addComment method.
	* addComment should add comment to private array
	*/
	@Test
	public void whenAddOneCommentThenOnlyOneCommentReturned() {
		String ticketName = "Ticket name";
		String ticketDescription = "Ticket description";
		String comment = "comment";
		Ticket ticket = new Ticket(ticketName, ticketDescription);
		ticket.addComment(comment);
		String[] expectedComments = {comment};
		assertThat("Only one comment should be returned from getComments method", ticket.getComments(), is(expectedComments));
	}

	/**
	* Test addComment method.
	* add multiple comments. should return array with size
	* equal to added comments number
	*/
	@Test
	public void whenAddMultipleCommentThenEqualArrayReturned() {
		String ticketName = "Ticket name";
		String ticketDescription = "Ticket description";
		String comment = "comment";
		Ticket ticket = new Ticket(ticketName, ticketDescription);
		int commentsNumber = 11;
		String[] expectedComments = new String[commentsNumber];
		for (int i = 0; i < commentsNumber; i++) {
			ticket.addComment(comment);
			expectedComments[i] = comment;
		}
		assertThat("Only one comment should be returned from getComments method", ticket.getComments(), is(expectedComments));
		assertThat("Only one comment should be returned from getComments method", ticket.getComments()[0], is(comment));
	}
}
