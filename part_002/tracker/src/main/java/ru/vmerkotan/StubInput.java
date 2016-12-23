package ru.vmerkotan;

/**
* The {@code StubInput} class represents Input interface for Tests.
* @since  1.0
*/
public class StubInput implements Input {
	/**
	 * Holds answers.
	 */
	private String[] answers;
    /**
     * Answers current position.
     */
	private int position = 0;

    /**
     * Constructs new object.
     * @param answers String[]
     */
	public StubInput(String[] answers) {
		this.answers = answers;
	}

    /**
     * Returns answer from answers array.
     * @param question	Question to ask user
     * @return position from range.
     */
	public String ask(String question) {
		return answers[position++];
	}

    /**
     * Asks question and expect answer from range.
     * @param question question to ask
     * @param range int[] range op possible answers
     * @return position from range.
     */
	public int ask(String question, int[] range) {
		return Integer.valueOf(this.ask(question));
	}
    /**
     * Asks question and expect answer from range.
     * @param question question to ask
     * @param range long[] range op possible answers
     * @return position from range.
     */
	public long ask(String question, long[] range) {
		return Long.valueOf(this.ask(question));
	}
}