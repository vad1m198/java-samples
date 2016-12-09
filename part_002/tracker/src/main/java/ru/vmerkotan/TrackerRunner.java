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
		UserAction FilterItemsCreatedAfter = new UserAction() {
		/*
		* returns UserAction key
		*/
		public int key() {
			return 6;
		}
		
		/*
		* Performs filtering Items by createdDate which created date is greater then passed value
		* Prints Items to console
		* @param input 	 Input system instance
		* @param tracker Tracker instance to work with
		*/
		public void execute(Input input, Tracker tracker) {			
			String createdDateStr = input.ask("Please type target created date:");
			Ticket[] tickets = new Ticket[0];
			try {
				long createdDate = Long.parseLong(createdDateStr);				
				for(Ticket ticket: tracker.filterTicketsCreatedAfter(createdDate)) {
					System.out.println(String.format("%s. %s. %s", ticket.getId(), ticket.getName(), ticket.getDescription()));
					for(String s: ticket.getComments()) {
						System.out.println("	Comment: " + s);
					}
				}
			} catch(NumberFormatException e) {
				System.out.println("[ERROR] Invalid Id. Please type valid Id number.");
			}			
		}
		
		/*
		* Returns key and name of action
		* @return String information about UserAction
		*/
		public String info() {
			return String.format("%s. %s", this.key(), "Filter Items by created name");
		}
				
		};
		menu.addAction(FilterItemsCreatedAfter);
		range = menu.getActionsKeys();
		
		do {
			menu.show();			
			menu.select(input.ask("select: ", range));
		} while(!"y".equals(this.input.ask("Exit? (y)")));
	}	
}