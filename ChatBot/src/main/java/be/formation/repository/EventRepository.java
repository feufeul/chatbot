package be.formation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import be.formation.beans.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer>{
	

}
