package ru.vmerkotan;

/**
* The {@code TrackerRunner} class represents a entry point
* to start tracker
* @author Vadim Merkotan
* @since  1.0
*/
public class TrackerRunner {
	
	private Input input;
	private Tracker tracker;
	private int[] range = new int[0];
	
	public TrackerRunner(Input i, Tracker tracker) {
		this.input = i;
		this.tracker = tracker;
		
	}
	
	public static void main(String [] args) {			
		Input input = new ValidateInput();
		Tracker tracker = new Tracker();
		new TrackerRunner(input, tracker).init();
		
	}
	
	public void init() {		
		MenuTracker menu = new MenuTracker(this.input, tracker);
		menu.fillActions();
		range = menu.getActionsKeys();
		
		do {
			menu.show();			
			menu.select(input.ask("select: ", range));
		} while(!"y".equals(this.input.ask("Exit? (y)")));
	}	
}