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
	public List<ChatUser> findAllUser();
	
	/**
	 * Delete the moderator status from a user, from chat and db
	 */
	public void unModerator(String name);
	
	/**
	 * Create an event where everybody can participate
	 * @param sender of the event
	 * @param message in the form of "!event yyyy MM dd hh mm"  
	 */
	void createEvent(String sender, String message);
	
	/**
	 * Find all events in db
	 * @return
	 */
	public List<Event> findAllEvents();
	
	/**
	 * 
	 * @param sender, name of the participant
	 * @param message sent by the sender
	 */
	public void participateEvent(String sender, String message);

}
