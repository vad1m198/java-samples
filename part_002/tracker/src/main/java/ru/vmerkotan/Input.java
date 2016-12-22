package ru.vmerkotan;

/**
* The {@code Input} class represents an interaction
* with user.
* @since  1.0
*/
public interface Input {

	/**
	* Asks  a question.
	* @param question	Question to ask user
	* @return String	answer
	*/
	String ask(String question);

	/**
	 * Asks a question. Works with ids
	 * @param question question to ask
	 * @param range long[] range op possible answers
	 * @return id
	 */
	int ask(String question, int[] range);

	/**
	 * Asks a question. Works with ids
	 * @param question question to ask
	 * @param range long[] range op possible answers
	 * @return id
	 */
	long ask(String question, long[] range);
}