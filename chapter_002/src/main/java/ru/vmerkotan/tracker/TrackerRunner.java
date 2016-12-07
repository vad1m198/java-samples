package ru.vmerkotan.tracker;

/**
* The {@code TrackerRunner} class represents a entry point
* to start tracker
* @author Vadim Merkotan
* @since  1.0
*/
public class TrackerRunner {
	
	private Input input;
	private Tracker tracker;
	
	public TrackerRunner(Input i) {
		this.input = i;
		
	}
	
	public static void main(String [] args) {			
		Input input = new ConsoleInput();			
		new TrackerRunner(input).init();
		
	}
	
	public void init() {
		tracker = new Tracker();
		MenuTracker menu = new MenuTracker(this.input, tracker);
		menu.fillActions();
		do {
			menu.show();
			int key = Integer.valueOf(input.ask("select: "));
			menu.select(key);
		} while(!"y".equals(this.input.ask("Exit? y")));
		//boolean exit = false;
		/*while(!exit) {
			showMenu();
			String menuItem = this.input.ask("Please select menu item");
			if(menuItem.equals("1")) {			
				printTicketsToConsole(this.tracker.getAllTickets());
			} else if(menuItem.equals("2")) {
				String ticketName = this.input.ask("Please type ticket name:");
				String ticketDescription = this.input.ask("Please type ticket description:");
				Ticket ticket = new Ticket(ticketName, ticketDescription);
				tracker.addTicket(ticket);
				System.out.println("Ticket was created successfully. Ticket Id is: " + ticket.getId());
			} else if(menuItem.equals("3")) {
				String ticketId = this.input.ask("Please type ticket id:");
				try{
					long ticketIdL = Long.parseLong(ticketId);
					tracker.deleteTicketById(ticketIdL);
				} catch(Exception e) {}
			} else if(menuItem.equals("4")) {
				String ticketId = this.input.ask("Please type ticket id:");
				String newName = this.input.ask("Please type new Name:");
				String newDescription = this.input.ask("Please type new Description:");
				tracker.updateTicket(Long.parseLong(ticketId), newName, newDescription);
			} else if(menuItem.equals("5")) {
				String ticketId = this.input.ask("Please type ticket id:");
				String comment = this.input.ask("Please type comment:");
				try{
					long ticketIdL = Long.parseLong(ticketId);
					tracker.addCommentToTicket(ticketIdL, comment);
				} catch(Exception e) {}
			} else if(menuItem.equals("6")) {			
				String matchString = this.input.ask("Please type string to search in name field:");	
				printTicketsToConsole(this.tracker.filterTicketsByName(matchString));
			} else if(menuItem.equals("7")) {			
				String date = this.input.ask("Please date in miliseconds to filter Tickets crated after:");			
				printTicketsToConsole(this.tracker.filterTicketsCreatedAfter(Long.parseLong(date)));			
			} else if(menuItem.equals("0")) {
				exit = true;
			}
		}*/
	}
	
	private void printTicketToConsole(Ticket t) {		
		System.out.println("Ticket Id: " + t.getId() + " Tikcet name: " + t.getName() + " Ticket description: " + t.getDescription());
	}
	
	private void printTicketsToConsole(Ticket[] tickets) {
		for(Ticket t: tickets) {
			printTicketToConsole(t);
		}		
	}
	
	private void showMenu() {				
		System.out.println("Menu:");
		System.out.println("1 - Show all tickets");
		System.out.println("2 - Add Ticket");
		System.out.println("3 - Remove Ticket");
		System.out.println("4 - Update Ticket");
		System.out.println("5 - Add Comment");
		System.out.println("6 - Filter tickets by name");
		System.out.println("7 - Show tickets created after date");
		System.out.println("0 - Exit");
	}
	
}