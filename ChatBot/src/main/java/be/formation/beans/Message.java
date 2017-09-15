package be.formation.beans;

import java.time.LocalDateTime;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import be.formation.utils.LocalDateTimeAttributeConverter;


@Entity
@Table(name="messages")
public class Message {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Convert(converter = LocalDateTimeAttributeConverter.class)
	private LocalDateTime date;
	private String message;
	@ManyToOne(fetch=FetchType.LAZY)
	private ChatUser user;
	
	public Message() {
		
	}
	

	public Message(String message, ChatUser user) {
		super();
		this.date = LocalDateTime.now();
		this.message = message;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ChatUser getUser() {
		return user;
	}

	public void setUser(ChatUser user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return date + " : " +user + " : "+ message; 
	}
	
	
	
	
}
