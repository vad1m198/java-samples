package ru.vmerkotan;

/**
* The {@code EditTicket} class represents edit Tickets UserAction	
* @since  1.0
*/
class EditTicket implements UserAction {
			
	/*
	* returns UserAction key
	*/
	public int key() {
		return 2;
	}
	
	/*
	* Performs editing Ticket based on passed id
	* @param input 	 Input system instance
	* @param tracker Tracker instance to work with
	*/
	public void execute(Input input, Tracker tracker) {
		String ticketIdStr = input.ask("Please type ticket Id:");
		String ticketUpdatedName = input.ask("Please type new ticket name:");
		String ticketUpdatedDescription = input.ask("Please type new ticket description:");			
		try {
			long ticketId = Long.parseLong(ticketIdStr);
			tracker.updateTicket(ticketId, ticketUpdatedName, ticketUpdatedDescription);
		} catch(Exception e) {/*NOP*/}
	}
	/*
	* Returns key and name of action
	* @return String information about UserAction
	*/
	public String info() {
		return String.format("%s. %s", this.key(), "Edit Item");
	}
}

/**
* The {@code MenuTracker} class represents a user menu
* to interact with tracker system
* @author Vadim Merkotan
* @since  1.0
*/
public class MenuTracker {
	
	private Input input;
	private Tracker tracker;
	private UserAction[] actions = new UserAction[7];
	
	public MenuTracker (Input input, Tracker tracker) {
		this.input = input;
		this.tracker = tracker;
	}
	
	public void fillActions() {
		this.actions[0] = this.new AddItem();
		this.actions[1] = new MenuTracker.ShowItems();
		this.actions[2] = new EditTicket();
		this.actions[3] = this.new DeleteItem();
		this.actions[4] = this.new AddComment();
		this.actions[5] = this.new FilterItemsByName();
		this.actions[6] = this.new FilterItemsCreatedAfter();
	}
	
	/*
	* Selects appropriate menu item
	* @param key int numberof action
	*/
	public void select(int key) {
		this.actions[key].execute(this.input, this.tracker);
	}
	/*
	* Prints tracker menu to console
	*/
	public void show() {
		for(UserAction action: this.actions) {
			if(action != null) {
				System.out.println(action.info());
			}			
		}
	}
	
	/**
	* The {@code AddItem} class represents add Ticket USerAction
	* @author Vadim Merkotan
	* @since  1.0
	*/
	private class AddItem implements UserAction {
		
		/*
		* returns UserAction key
		*/
		public int key() {
			return 0;
		}
		/*
		* Performs creating and adding new Ticket
		* to tracker
		* @param input 	 Input system instance
		* @param tracker Tracker instance to work with
		*/
		public void execute(Input input, Tracker tracker) {
			String ticketName = input.ask("Please type ticket name:");
			String ticketDescription = input.ask("Please type ticket description:");			
			tracker.addTicket(new Ticket(ticketName, ticketDescription));
		}
		
		/*
		* Returns key and name of action
		* @return String information about UserAction
		*/
		public String info() {
			return String.format("%s. %s", this.key(), "Add new Item");
		}
	
	}
	
	/**
	* The {@code DeleteItem} class represents delete Ticket UserAction
	* @author Vadim Merkotan
	* @since  1.0
	*/
	private class DeleteItem implements UserAction {
		
		/*
		* returns UserAction key
		*/
		public int key() {
			return 3;
		}
		/*
		* Performs deleting Ticket object
		* from tracker
		* @param input 	 Input system instance
		* @param tracker Tracker instance to work with
		*/
		public void execute(Input input, Tracker tracker) {
			String ticketIdStr = input.ask("Please type ticket Id:");
			try {
				long ticketId = Long.parseLong(ticketIdStr);
				tracker.deleteTicketById(ticketId);
			} catch(Exception e){/*NOP*/}
		}
		
		/*
		* Returns key and name of action
		* @return String information about UserAction
		*/
		public String info() {
			return String.format("%s. %s", this.key(), "Delete Item");
		}
	}
	
	/**
	* The {@code AddComment} class represents add comment to
	* ticket user action
	* @author Vadim Merkotan
	* @since  1.0
	*/
	private class AddComment implements UserAction {
		
		/*
		* returns UserAction key
		*/
		public int key() {
			return 4;
		}
		/*
		* Performs adding comment to Ticket object
		* @param input 	 Input system instance
		* @param tracker Tracker instance to work with
		*/
		public void execute(Input input, Tracker tracker) {
			String ticketIdStr = input.ask("Please type ticket Id:");
			String comment = input.ask("Please type your comment:");
			try {
				long ticketId = Long.parseLong(ticketIdStr);
				tracker.addCommentToTicket(ticketId, comment);
			} catch(Exception e){/*NOP*/}
		}
		
		/*
		* Returns key and name of action
		* @return String information about UserAction
		*/
		public String info() {
			return String.format("%s. %s", this.key(), "Add comment to Item");
		}
	}
	
	/**
	* The {@code FilterItemsByName} class represents filetr tickets
	* user action based on name
	* @author Vadim Merkotan
	* @since  1.0
	*/
	private class FilterItemsByName implements UserAction {
		
		/*
		* returns UserAction key
		*/
		public int key() {
			return 5;
		}
		
		/*
		* Performs filtering Items by name by contains matcher
		* Prints Items to console
		* @param input 	 Input system instance
		* @param tracker Tracker instance to work with
		*/
		public void execute(Input input, Tracker tracker) {
			String filterStr = input.ask("Please type filter string:");
			for(Ticket ticket: tracker.filterTicketsByName(filterStr)) {
				System.out.println(String.format("%s. %s. %s", ticket.getId(), ticket.getName(), ticket.getDescription()));
				for(String s: ticket.getComments()) {
					System.out.println("	Comment: " + s);
				}
			}
			
		}
		
		/*
		* Returns key and name of action
		* @return String information about UserAction
		*/
		public String info() {
			return String.format("%s. %s", this.key(), "Filter Items by name");
		}
	}
	
	/**
	* The {@code FilterItemsCreatedAfter} class represents filter tickets
	* user action based on createdDate
	* @author Vadim Merkotan
	* @since  1.0
	*/
	private class FilterItemsCreatedAfter implements UserAction {
		
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
			} catch(Exception e){/*NOP*/}
			
			
			
		}
		
		/*
		* Returns key and name of action
		* @return String information about UserAction
		*/
		public String info() {
			return String.format("%s. %s", this.key(), "Filter Items by created name");
		}
	}
	
	/**
	* The {@code ShowItems} class represents show all Tickets UserAction	
	* @since  1.0
	*/
	private static class ShowItems implements UserAction {
		/*
		* returns UserAction key
		*/
		public int key() {
			return 1;
		}
	
		/*
		* Performs printing to console information about
		* Tickets placed in Tracker
		* @param input 	 Input system instance
		* @param tracker Tracker instance to work with
		*/
		public void execute(Input input, Tracker tracker) {
			for(Ticket ticket: tracker.getAllTickets()) {
				System.out.println(String.format("%s. %s. %s", ticket.getId(), ticket.getName(), ticket.getDescription()));
				for(String s: ticket.getComments()) {
					System.out.println("	Comment: " + s);
				}
			}			
		}
		/*
		* Returns key and name of action
		* @return String information about UserAction
		*/
		public String info() {
			return String.format("%s. %s", this.key(), "Show all tickets");
		}
	
	}
	
}