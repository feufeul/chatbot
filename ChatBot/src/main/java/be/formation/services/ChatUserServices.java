package be.formation.services;


import java.time.LocalDateTime;
import java.util.List;

import be.formation.beans.ChatUser;
import be.formation.beans.Event;

public interface ChatUserServices {
	
	public void updateEvent(int id, LocalDateTime date, String description);

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
	 * Deleting a chatter from db
	 * @param usr
	 */
	public void deleteUser(ChatUser usr);
	
	
	/**
	 * Find the user in the database
	 * @param The nickname of the chatter
	 * @return An entity of the ChatUser
	 */
	public ChatUser findOneUser(String str);

	/**
	 * Find all users in db
	 * @return A List of all the users
	 */
	public List<ChatUser> findAllUser();
	
	/**
	 * Promote a chatter to moderator
	 * @param name moderator
	 */
	public void upModerator(String name);
	
	/**
	 * Unpromote a chatter from moderator
	 * @param name of the chatter
	 */
	public void downModerator(String name);
	
	/**
	 * Create an event where everybody can participate
	 * @param sender of the event
	 * @param message in the form of "!event yyyy MM dd hh mm"  
	 */
	public void createEvent(String sender, String message);
	
	/**
	 * Delete event from database
	 * @param event
	 */
	public void deleteEvent(Event event);
	
	/**
	 * Find all events in db
	 * @return
	 */
	
	/**
	 * Find one event in db
	 * @param id
	 * @return
	 */
	public Event findOneEvent(int id);
	
	/**
	 * Find all events in db
	 * @return a list of events
	 */
	public List<Event> findAllEvents();
	
	/**
	 * 
	 * @param sender, name of the participant
	 * @param message sent by the sender
	 */
	public void participateEvent(String sender, String message);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public List<ChatUser> getParticipants(int id);

}
