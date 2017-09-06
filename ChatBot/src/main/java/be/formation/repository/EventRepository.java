package be.formation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import be.formation.beans.Event;

public interface EventRepository extends JpaRepository<Event, Integer>{

}
