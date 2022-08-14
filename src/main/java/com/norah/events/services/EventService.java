package com.norah.events.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.norah.events.models.Event;
import com.norah.events.models.User;
import com.norah.events.repositories.EventRepository;

@Service
public class EventService {
	private final EventRepository eventRepo;
	private final UserService userServ;

	public EventService(EventRepository eventRepo, UserService userServ) {
		this.eventRepo = eventRepo;
		this.userServ = userServ;
	}

	public Event findEvent(Long id) {
		Optional<Event> foundEvent = eventRepo.findById(id);
		if (foundEvent.isPresent()) {
			return foundEvent.get();
		}
		return null;
	}

	public List<Event> findAll() {
		return eventRepo.findAll();
	}

	public Event createEvent(Event event, Long user_id) {
		System.out.println("inside createEvent in event service \n \n");
		User host = userServ.findUser(user_id);
		event.setHost(host);
		return eventRepo.save(event);
	}

	public List<Event> eventsByCurrentUser(Long user_id) {
		User host = userServ.findUser(user_id);
		return host.getCreatedEvents();
	}

	public List<Event> notJoindEventByCurrentUser(User user) {

		List<Event> notJoinedEvents = new ArrayList<Event>();
		boolean notJoined = true;

		for (Event event : findAll()) {
			if (event.getHost() == user) {
				notJoined = false;
			} else {
				for (User joinedUser : event.getJoinedUsers())
					if (joinedUser == user) {
						notJoined = false;
						break;
					}
			}
			if (notJoined) {
				notJoinedEvents.add(event);
			}
		}
		return notJoinedEvents;
	}
	
	public Event updateEvent(Long eventId, Event updatedCopy) {
		Event foundEvent = findEvent(eventId);
		foundEvent.setDate(updatedCopy.getDate());
		foundEvent.setLocation(updatedCopy.getLocation());
		foundEvent.setName(updatedCopy.getName());
		return eventRepo.save(foundEvent);
	}
	
	
	public void deleteEvent(Long eventId) {
		Event foundEvent = findEvent(eventId);
		eventRepo.delete(foundEvent);
	}

	public boolean joinEvent(Long eventId, User user) {
		Event foundEvent = findEvent(eventId);
		System.out.println("inside joinEvent: \n \n"+ foundEvent);
		if (foundEvent == null ) {return false;}
		foundEvent.getJoinedUsers().add(user);
		eventRepo.save(foundEvent);
		return true;
	}
	
	public boolean notJoinEvent(Long eventId, User user) {
		Event foundEvent = findEvent(eventId);
		if (foundEvent == null ) {return false;}
		foundEvent.getJoinedUsers().remove(user);
		eventRepo.save(foundEvent);
		userServ.saveChanges(user);
		return true;
	}
//	public List<Event> eventsByCurrentUser(Long user_id){
//		User host= userServ.findUser(user_id);
//		List<Event> hostEvents = new ArrayList<Event>();
//		for(Event event: findAll()) {
//			if (event.getHost() == host) {
//				hostEvents.add(event);
//			}
//		}
//		return hostEvents;
//	}

}
