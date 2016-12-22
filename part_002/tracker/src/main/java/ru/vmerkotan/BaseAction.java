package ru.vmerkotan;

/**
* The {@code BaseAction} class represents an abstrack
* implementation of UserAction.
* @since  1.0
*/
public abstract class BaseAction implements UserAction {
	/**
	 * Action name.
	 */
	private String name;

    /**
     * Constructs new object.
     * @param name String name of action
     */
	public BaseAction(String name) {
		this.name = name;
	}

	/**
	* returns UserAction key.
    * @return int abstract action key
	*/
	public abstract int key();

	/**
	* Performs UserAction.
	* @param input 	 Input system instance
	* @param tracker Tracker instance to work with
	*/
	public abstract void execute(Input input, Tracker tracker);

	/**
	* Returns key and name of action.
	* @return String information about UserAction
	*/
	public String info() {
		return String.format("%s. %s", this.key(), this.name);
	}

}