package be.formation.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="users")
public class ChatUser{

	/**
	 * Chatter's name, id for the database
	 */
	@Id
	private String name;
	private int messagesSent;
	private boolean isModerator;
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "event_chatuser", joinColumns = @JoinColumn(name = "chatuser_name"), inverseJoinColumns = @JoinColumn(name="event_id"))
	private List<Event> events;
	@OneToMany(mappedBy="user")
	private List<Message> messages;
	
	ChatUser(){
	}

	/**
	 * ChatUser's constructor
	 * @param name used by chatter
	 */
	public ChatUser(String name) {
		this.name =name;
		this.messagesSent=1;
		this.isModerator=false;
		this.events = new ArrayList<>();
	}

	/**
	 * Getter for the chatter's name
	 * @return chatter's name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Check if a User is a moderator, need to use GetIsModerator, because Thymeleaf is looking for Getter beginning with "get"
	 * @return
	 */
	public boolean getIsModerator() {
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
	public int incrmessagesSent() {
		return ++messagesSent;
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
	
	
	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	@Override
	public String toString() {
		return "ChatUser [name=" + name + ", messagesSent=" + messagesSent + ", isModerator=" + isModerator + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChatUser other = (ChatUser) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	
}
