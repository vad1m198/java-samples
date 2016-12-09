package ru.vmerkotan;

import java.util.Arrays;
/**
* The {@code Tracker} class represents a Tracker instance
* to hold tickets
* @author Vadim Merkotan
* @since  1.0
*/
public class Tracker {
	private Ticket[] tickets = new Ticket[10];
	private int position = 0;
	
	/**
	* adds new Ticket to tracker
	* If tickets array is full then new array
	* should be created and all values from old one 
	* should be copied to new array
	*
	* @param ticket	ticket should be added
	*/
	public void addTicket(Ticket ticket) {
		if(ticket != null) {
			if(position == this.tickets.length) {
				Ticket[] newTicketsArr = Arrays.copyOf(this.tickets, this.tickets.length * 2);
				this.tickets = newTicketsArr;
			}
		}
		this.tickets[position++] = ticket;
	}
	
	/**
	* returns all tickets present in tracker
	* without null values
	*
	* @return all tickets
	*/
	public Ticket[] getAllTickets() {		
		Ticket [] result = new Ticket[this.position];
		int counter = 0;
		for(int i = 0; i < this.tickets.length; i++) {			
			if(this.tickets[i] != null) {
				result[counter++] = this.tickets[i];
			}
		}
		return Arrays.copyOf(result, counter);
		
		
	}
	
	/**
	* returns Ticket which Id equals to passed Id
	*
	* @param  ticketId	search Ticket id
	* @return Ticket which Id equal to ticketId
	*/
	public Ticket getTicketById(long ticketId) {
		Ticket result = null;
		for(Ticket t: this.tickets) {
			if(t != null && t.getId() == ticketId) {
				result = t;
				break;
			}
		}
		return result;
	}
	
	/**
	* deletes Ticket by passed Ticket Id
	* places null to ticket position
	*
	* @param  ticketId	Ticket id which should be deleted
	*/
	public void deleteTicketById(long ticketId) {		
		for(int i = 0; i < this.tickets.length; i++) {
			if(this.tickets[i] != null && this.tickets[i].getId() == ticketId) {
				this.tickets[i] = null;
				break;
			}
		}
	}
	
	/**
	* adds comment to specified Ticket
	*
	* @param  ticketId	Ticket id where comment should be added
	* @param  comment	Comment which should be added
	*/
	public void addCommentToTicket(long ticketId, String comment) {
		Ticket ticket = this.getTicketById(ticketId);
		if(ticket != null) {
			ticket.addComment(comment);
		}
		
	}
	
	/**
	* updates Ticket with new one
	*
	* @param  ticketId	ID of Ticket to be updated
	* @param  ticket	Ticket updated Ticket object
	*/
	public void updateTicket(long ticketId, Ticket ticket) {		
		for(int i = 0; i < this.tickets.length; i++ ) {
			if(this.tickets[i] != null && this.tickets[i].getId() == ticketId) {
				ticket.setId(ticketId);
				this.tickets[i] = ticket;
			}
		}
		
	}
	
	/**
	* returns tickets which name field contains passed value
	*
	* @param  partName
	* @return Ticket[] which satisfy passed part name
	*/
	public Ticket[] filterTicketsByName(String partName) {		
		Ticket[] result = new Ticket[this.tickets.length];
		int counter = 0;
		if(partName != null) {
			for(Ticket t: this.getAllTickets()) {
				if(t.getName().toLowerCase().contains(partName.toLowerCase())) {
					result[counter++] = t;
				}
			}
		}
		
		return Arrays.copyOf(result, counter);
	}
	
	/**
	* returns tickets which was createdDate after
	* passed date
	*
	* @param  targetDate long (date in miliseconds)
	* @return Ticket[] which satisfy passed targetDate
	*/
	public Ticket[] filterTicketsCreatedAfter(long targetDate) {
		Ticket[] result = new Ticket[this.tickets.length];
		int counter = 0;
		for(Ticket t: this.getAllTickets()) {
			if(t.getCreatedDate() >= targetDate) {
				result[counter++] = t;
			}
		}
		return Arrays.copyOf(result, counter);
	}
	
	
	
}