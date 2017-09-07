package be.formation.beans;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

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
	private long id;
	@Convert(converter = LocalDateTimeAttributeConverter.class)
	private LocalDateTime date;
	private String description;
	@ManyToMany(mappedBy="events")
	private List<ChatUser> users;
	
	public Event() {

	}
	
	public Event( String description,LocalDateTime date) {
		super();
		this.date = date;
		this.description = description;
		this.users = new ArrayList<>();
	}
	
	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setId(long id) {
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
		return "Event [id=" + id + ", date=" + date + ", description=" + description + ", users=" + users + "]";
	}

	
	

}
