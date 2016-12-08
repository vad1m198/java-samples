package ru.vmerkotan.interfaces;

import ru.vmerkotan.Ticket;
/*
 * TicketManagers should implement this interface.
 */
public interface ITicketManager {	
	
	/*
	* adds Ticket to Manager
	*/
	void addTicket(Ticket t);
	
	/*
	* removes Ticket to Manager
	*/
	void removeTicket(Ticket t);
	
	/*
	* returns related Ticket objects
	*/
	Ticket[] getAllTickets();
		
}