package ru.vmerkotan;

import java.util.Arrays;
import  java.util.Random;
/**
* The {@code Ticket} class represents an item in Tracker instance.
* @author Vadim Merkotan
* @since  1.0
*/
public class Ticket {
    /**
     * Ticket id.
     */
	private long id;
    /**
     * Ticket name.
     */
	private String name;
    /**
     * Ticket description.
     */
	private String description;
    /**
     * Ticket created date.
     */
	private long createdDate;
    /**
     * Ticket comments array.
     */
	private String[] comments = new String[10];
    /**
     * Current comment position.
     */
	private int commentPossition = 0;
    /**
     * Holds Random object.
     */
	static final Random RANDOM = new Random();

	/**
	* initiates new Ticket object.
	* @param name			Ticket name
	* @param description	Ticket description
	*/
	public Ticket(String name, String description) {
		this.name = name;
		this.description = description;
		this.id = System.currentTimeMillis() + this.RANDOM.nextInt(100);
		this.createdDate = System.currentTimeMillis();
	}

	/**
	* adds new comment to comments array.
	* If comments array is full then new array
	* should be created and all values from old one
	* should be copied to new array
	* @param comment	comment which should be added
	*/
	public void addComment(String comment) {
		if (comment != null) {
			if (commentPossition == this.comments.length) {
				String[] newCommentsArr = Arrays.copyOf(this.comments, this.comments.length * 2);
				this.comments = newCommentsArr;
			}
			this.comments[commentPossition++] = comment;
		}
	}

	/**
	* returns value of id field.
	* @return	Id field value
	*/
	public long getId() {
		return this.id;
	}

	/**
	* sets Ticket passed Id.
	* @param	id for field value
	*/
	public void setId(long id) {
		this.id = id;
	}

	/**
	* returns value of name field.
	* @return	name field value
	*/
	public String getName() {
		return this.name;
	}

	/**
	* returns value of description field.
	* @return	description field value
	*/
	public String getDescription() {
		return this.description;
	}

	/**
	* returns value of createdDate field.
	* @return	createdDate field value
	*/
	public long getCreatedDate() {
		return this.createdDate;
	}

	/**
	* returns all related not null comments.
	* @return	related comments
	*/
	public String[] getComments() {
		String[] result = new String[commentPossition];
		for (int i = 0; i < this.comments.length; i++) {
			if (this.comments[i] == null) {
				break;
			}
			result[i] = this.comments[i];
		}
		return result;
	}

	/**
	* sets value to name field.
	* @param    name new name
	*/
	public void setName(String name) {
		this.name = name;
	}

	/**
	* sets value to description field.
	* @param	description new description
	*/
	public void setDescription(String description) {
		this.description = description;
	}
}