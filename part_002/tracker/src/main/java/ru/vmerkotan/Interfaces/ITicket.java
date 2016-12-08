package ru.vmerkotan.interfaces;
/*
 * Tickets should implement this interface.
 * not used. 
 * 3. Проанализировать полученный код и попытаться выделить в нем группы классов и методов 
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