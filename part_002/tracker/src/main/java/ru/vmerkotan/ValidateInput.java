package ru.vmerkotan;

/**
 * Class represents instance of COnsoleInput to validate data.
 */
public class ValidateInput extends ConsoleInput {
    /**
     * Asks question and validates answer.
     * @param question question to ask
     * @param range int[] range op possible answers
     * @return value from range
     */
	public int ask(String question, int[] range) {
		boolean invalid = true;
		int value = -1;
		do {
			try {
				value = super.ask(question, range);
				invalid = false;
			} catch (NumberFormatException nfe) {
				System.out.println("Please enter valid data again.");
			} catch (MenuOutException moe) {
				System.out.println("Please select key from menu range.");
			}
		} while (invalid);
		return value;
	}

    /**
     * Asks question and validates answer.
     * @param question question to ask
     * @param range long[] range op possible answers
     * @return value from range
     */
	public long ask(String question, long[] range) {
		boolean invalid = true;
		long value = -1L;
		do {
			try {
				value = super.ask(question, range);
				invalid = false;
			} catch (NumberFormatException nfe) {
				System.out.println("Please enter valid data again.");
			} catch (InvalidIdintifierException iid) {
				System.out.println("Please type valid Ticket id.");
				for (long l: range) {
					System.out.println(l);
				}
			}
		} while (invalid);
		return value;
	}
}