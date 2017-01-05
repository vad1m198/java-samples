package ru.vmerkotan;

/**
* The {@code TrackerRunner} class represents a entry point
* to start tracker.
* @author Vadim Merkotan
* @since  1.0
*/
public class TrackerRunner {
	/**
	 * Gets some inputs.
	 */
	private final Input input;
    /**
     * MenuTracker instance.
     */
    private final MenuTracker menu;

    /**
     * Creates new TrackerRunner object.
     * @param i Input
     * @param menu MenuTracker
     */
    TrackerRunner(final Input i, final MenuTracker menu) {
		this.input = i;
		this.menu = menu;
	}

    /**
     * Runs Tracker app.
     * @param args String[] params
     */
	public static void main(String[] args) {
		Input input = new ValidateInput();
		Tracker tracker = new Tracker();
		MenuTracker menu = new MenuTracker(input, tracker);
		menu.fillActions();
		new TrackerRunner(input, menu).init();
	}

    /**
     * Initiates runner.
     */
    void init() {

        int[] range = this.menu.getActionsKeys();
		do {
            this.menu.show();
            this.menu.select(this.input.ask("select: ", range));
		} while (!"y".equals(this.input.ask("Exit? (y)")));
	}
}