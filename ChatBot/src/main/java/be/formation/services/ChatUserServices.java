package be.formation.services;


import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import be.formation.beans.ChatUser;
import be.formation.beans.Event;

public interface ChatUserServices {

	/**
	 * Increment de number of messages that have been sent
	 * @param usr
	 */
	public void incrMessagesSent(ChatUser usr);

	/**
	 * Creating a new User in the database
	 * @param usr
	 */
	public void createUser(ChatUser usr);
	
	/**
	 * Find the user in the database
	 * @param The nickname of the chatter
	 * @return An entity of the ChatUser
	 */
	public ChatUser findOne(String str);

	/**
	 * Find all users in db
	 * @return A List of all the users
	 */
	public List<ChatUser> findAll();
	
	/**
	 * Delete the moderator status from a user, from chat and db
	 */
	public void unModerator(String name);
	
	/**
	 * Create an event where everybody can participate
	 * @param name of the event
	 * @param date of the event
	 */
	void createEvent(String sender, String message);

}
