package ru.vmerkotan;

/**
* The {@code EditTicket} class represents edit Tickets UserAction	
* @since  1.0
*/
class EditTicket extends BaseAction {
	
	public EditTicket(String actionName) {
		super(actionName);
	}
			
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
			Ticket ticketToUpdate = new Ticket(ticketUpdatedName, ticketUpdatedDescription);
			ticketToUpdate.setName(ticketUpdatedName);
			ticketToUpdate.setDescription(ticketUpdatedDescription);
			tracker.updateTicket(ticketId, ticketToUpdate);						
		} catch(NumberFormatException e) {
			System.out.println("Invalid Id. Please type valid Id number.");
		}
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
	private int position = 0;
	
	public MenuTracker (Input input, Tracker tracker) {
		this.input = input;
		this.tracker = tracker;
	}
	
	public void fillActions() {
		this.actions[position++] = this.new AddItem("Add Item");
		this.actions[position++] = new MenuTracker.ShowItems("Show all Items");
		this.actions[position++] = new EditTicket("Edit Item");
		this.actions[position++] = this.new DeleteItem("Delete Item");
		this.actions[position++] = this.new AddComment("Comment Item");
		this.actions[position++] = this.new FilterItemsByName("Filter Items by name");
		//this.actions[position++] = this.new FilterItemsCreatedAfter();
	}
	
	public void addAction(UserAction action) {
		this.actions[position++] = action;
	}
	
	/*
	* Selects appropriate menu item
	* @param key int number of action
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
	
	/*
	* Invokes key method on each action from actions array
	* @return Array contains UserAction keys from this.actions field
	*/
	public int[] getActionsKeys() {
		int[] result = new int[this.actions.length];
		for(int i = 0; i < this.actions.length; i++) {
			result[i] = this.actions[i].key();
		}
		return result;
	}
	
	/**
	* The {@code AddItem} class represents add Ticket USerAction
	* @author Vadim Merkotan
	* @since  1.0
	*/
	private class AddItem extends BaseAction {
		
		public AddItem(String actionName) {
			super(actionName);
		}
		
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
	}
	
	/**
	* The {@code DeleteItem} class represents delete Ticket UserAction
	* @author Vadim Merkotan
	* @since  1.0
	*/
	private class DeleteItem extends BaseAction {
		
		public DeleteItem(String actionName) {
			super(actionName);
		}
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
			} catch(NumberFormatException e){
				System.out.println("[ERROR] Invalid Id. Please type valid Id number.");
			}
		}
	}
	
	/**
	* The {@code AddComment} class represents add comment to
	* ticket user action
	* @author Vadim Merkotan
	* @since  1.0
	*/
	private class AddComment extends BaseAction {
		
		public AddComment(String actionName) {
			super(actionName);
		}
		
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
			} catch(NumberFormatException e) {
				System.out.println("[ERROR] Invalid Id. Please type valid Id number.");
			}
		}
	}
	
	/**
	* The {@code FilterItemsByName} class represents filetr tickets
	* user action based on name
	* @author Vadim Merkotan
	* @since  1.0
	*/
	private class FilterItemsByName extends BaseAction {
		
		public FilterItemsByName(String actionName) {
			super(actionName);
		}
		
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
	}
	
	/**
	* The {@code ShowItems} class represents show all Tickets UserAction	
	* @since  1.0
	*/
	private static class ShowItems extends BaseAction {
		
		public ShowItems(String actionName) {
			super(actionName);
		}
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
	}
	
}