package ru.vmerkotan;

/**
* The {@code ConsoleInput} class represents an interaction
* with user through console.
* @since  1.0
*/
public class ConsoleInput implements Input {

	/**
	 * Scanner object to read user input.
	 */
	private Scanner scanner = new Scanner(System.in);

	/**
	* Asks  a question.
	* @param question	Question to ask user
	* @return String	answer readed from console
	*/
	public String ask(String question) {
		System.out.println(question);
		return this.scanner.nextLine();
	}

    /**
     * Asks a question.
     * @param question question to ask
     * @param range int[] range op possible answers
     * @return action key
     */
	public int ask(String question, int[] range) {
		int key = Integer.valueOf(this.ask(question));
		boolean exist = false;
		for (int value: range) {
			if (value == key) {
				exist = true;
				break;
			}
		}
		if (exist) {
			return key;
		} else {
			throw new MenuOutException("Out of menu range.");
		}
	}

    /**
     * Asks a question. Works with ids
     * @param question question to ask
     * @param range long[] range op possible answers
     * @return id
     */
	public long ask(String question, long[] range) {
		long key = Integer.valueOf(this.ask(question));
		boolean exist = false;
		for (long value: range) {
			if (value == key) {
				exist = true;
				break;
			}
		}
		if (exist) {
			return key;
		} else {
			throw new InvalidIdintifierException("Not in idintifiers range.");
		}
	}
}