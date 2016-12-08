package ru.vmerkotan.tracker;

class EditTicket implements UserAction {
	public int key() {
		return 2;
	}
	
	public void execute(Input input, Tracker tracker) {
		Long ticketId = Long.valueOf(input.ask("Please type ticket Id:"));
		String ticketUpdatedName = input.ask("Please type new ticket name:");
		String ticketUpdatedDescription = input.ask("Please type new ticket description:");			
		tracker.updateTicket(ticketId, ticketUpdatedName, ticketUpdatedDescription);
	}
	
	public String info() {
		return String.format("%s. %s", this.key(), "Edit Ticket");
	}
}

public class MenuTracker {
	
	private Input input;
	private Tracker tracker;
	private UserAction[] actions = new UserAction[5];
	
	public MenuTracker (Input input, Tracker tracker) {
		this.input = input;
		this.tracker = tracker;
	}
	
	public void fillActions() {
		this.actions[0] = this.new AddItem();
		this.actions[1] = new MenuTracker.ShowItems();
		this.actions[2] = new EditTicket();
	}
	
	public void select(int key) {
		this.actions[key].execute(this.input, this.tracker);
	}
	
	public void show() {
		for(UserAction action: this.actions) {
			if(action != null) {
				System.out.println(action.info());
			}			
		}
	}
	
	private class AddItem implements UserAction {
		public int key() {
			return 0;
		}
	
		public void execute(Input input, Tracker tracker) {
			String ticketName = input.ask("Please type ticket name:");
			String ticketDescription = input.ask("Please type ticket description:");			
			tracker.addTicket(new Ticket(ticketName, ticketDescription));
		}
	
		public String info() {
			return String.format("%s. %s", this.key(), "Add new Item");
		}
	
	}
	
	private static class ShowItems implements UserAction {
		public int key() {
			return 1;
		}
	
		public void execute(Input input, Tracker tracker) {
			for(Ticket ticket: tracker.getAllTickets()) {
				System.out.println(String.format("%s. %s. %s", ticket.getId(), ticket.getName(), ticket.getDescription()));
			}			
		}
	
		public String info() {
			return String.format("%s. %s", this.key(), "Show all tickets");
		}
	
	}
	
}