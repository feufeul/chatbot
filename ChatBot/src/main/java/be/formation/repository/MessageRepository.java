package be.formation.repository;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import be.formation.beans.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {
	
//	@Query(value="select distinct m.date from Message m inner join m.user ar order by m.date")
//	public LocalDateTime findLastDateByName();
	
	
}
