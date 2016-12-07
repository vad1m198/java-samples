package ru.vmerkotan.tracker.interfaces;

import ru.vmerkotan.tracker.*;
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