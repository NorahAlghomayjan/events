package com.norah.events.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.norah.events.models.Event;
import com.norah.events.models.User;


@Repository
public interface EventRepository extends CrudRepository<Event,Long> {
	
//	List<Event> findByCreatedBy(User createdBy);
//	List<Event> findByUsersNotIn(List<User> users);
//	List<Event> findByUsersIn(List<User> users);
	List<Event> findAll();

}
