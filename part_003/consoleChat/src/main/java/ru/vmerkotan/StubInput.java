package ru.vmerkotan;

/**
* The {@code StubInput} class represents Input interface for Tests.
* @since  1.0
*/
public class StubInput implements Input {
    /**
     * answers.
     */
	private String[] answers;
    /**
     * current position for answers array.
     */
	private int position = 0;

    /**
     * Constructs new object.
     * @param answers array of answers to use
     */
	public StubInput(String[] answers) {
		this.answers = answers;
	}

    /**
     * Returns string from answers array.
     * @return String answer
     */
	public String read() {
		return answers[position++];
	}
}