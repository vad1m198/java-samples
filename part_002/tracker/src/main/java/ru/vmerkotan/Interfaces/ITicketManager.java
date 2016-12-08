package ru.vmerkotan.interfaces;

import ru.vmerkotan.Ticket;
/*
 * TicketManagers should implement this interface.
 * not used. 
 * 3. Проанализировать полученный код и попытаться выделить в нем группы классов и методов 
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