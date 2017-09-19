package be.formation.beans;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;


import be.formation.utils.LocalDateTimeAttributeConverter;

/**
 * This class provides the possibility to create events
 * @author feufeul
 *
 */
@Entity
@Table(name="events")
public class Event {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	// Auto convert for date from sql and date from java 8
	@Convert(converter = LocalDateTimeAttributeConverter.class)
	private LocalDateTime date;
	private String description;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "event_chatuser", joinColumns = @JoinColumn(name = "event_id"), inverseJoinColumns = @JoinColumn(name="chatuser_name"))
	private List<ChatUser> users;
	
	public Event() {

	}
	
	public Event( String description,LocalDateTime date) {
		super();
		this.date = date;
		this.description = description;
		this.users = new ArrayList<>();
	}
	
	public Event(int id2, LocalDateTime date2, String description2) {
		super();
		this.id=id2;
		this.description=description2;
		this.date=date2;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<ChatUser> getUsers() {
		return users;
	}
	
	public void setUsers(List<ChatUser> users) {
		this.users = users;
	}
	
	public LocalDateTime getDate() {
		return date;
	}
	
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return  description+ ", " 
				+date.toLocalDate() + " à "
				+date.toLocalTime();
	}

	
	

}
