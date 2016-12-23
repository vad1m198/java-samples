package ru.vmerkotan;

/**
* The {@code UserAction} class based UserAction.
* @since  1.0
*/
public interface UserAction {

	/**
	* returns UserAction key.
	* @return key
	*/
	int key();

	/**
	* Performs UserAction.
	* @param input 	 Input system instance
	* @param tracker Tracker instance to work with
	*/
	void execute(Input input, Tracker tracker);

	/**
	* Returns key and name of action.
	* @return String information about UserAction
	*/
	String info();
}