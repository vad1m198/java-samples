package ru.vmerkotan.interfaces;
/*
 * Tickets should implement this interface.
 */
public interface ITicket {	
	
	/*
	* return Ticket Id;
	*/
	long getId();
	
	/*
	* Adds passed String to comments
	*/
	void addComment(String comment);

	/*
	* Returns all related comments
	*/
	String[] getComments();
	
	/*
	* Returns Ticket name
	*/
	String getName();
	
	/*
	* Sets name to passes value
	*/
	void setName(String name);
	
}