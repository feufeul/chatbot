package be.formation.beans;

import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Users")
public class ChatUser{

	/**
	 * Chatter's name, id for the database
	 */
	@Id
	private String name;
	private int messagesSent;
	private boolean isModerator;
	
	ChatUser(){
	}

	/**
	 * ChatUser's constructor
	 * @param name used by chatter
	 */
	ChatUser(String name) {

		this.name =name;
	}

	/**
	 * Getter for the chatter's name
	 * @return chatter's name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Check if a User is a moderator
	 * @return
	 */
	public boolean isModerator() {
		return isModerator;
	}

	/**
	 * If a User is moderator put him non-moderator, and the opposite too
	 */
	public void setModerator() {
		this.isModerator = !isModerator;
	}
	/**
	 * Getter for the messages sent
	 * @return number of messages sent
	 */
	public int getmessagesSent() {
		return messagesSent;
	}
	/**
	 * Increment the number of messages sent
	 */
	public void incrmessagesSent() {
		messagesSent++;
	}

	public int getMessagesSent() {
		return messagesSent;
	}

	public void setMessagesSent(int messagesSent) {
		this.messagesSent = messagesSent;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setModerator(boolean isModerator) {
		this.isModerator = isModerator;
	}


	
}
