package com.norah.events.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.norah.events.models.Comment;
import com.norah.events.models.Event;
import com.norah.events.models.User;
import com.norah.events.services.CommentService;
import com.norah.events.services.EventService;
import com.norah.events.services.UserService;

@Controller
@RequestMapping("/events")
public class EventController {

	private final EventService eventServ;
	private final UserService userServ;
	private final CommentService commentServ;
	private List<String> locations = Arrays.asList("Riyadh", "Jeddah", "Dammam", "Taif", "Abha", "Qassim");

	public EventController(EventService eventServ, UserService userServ,CommentService commentServ) {
		this.eventServ = eventServ;
		this.userServ = userServ;
		this.commentServ = commentServ;
	}

	// 1
	@GetMapping("")
	public String mainPage(Model model, HttpSession session, RedirectAttributes redirectAttributes,
			@Valid @ModelAttribute("event") Event event, BindingResult result) {

		if (checkUserLogged(session, redirectAttributes)) {
			return "redirect:/";
		}
		model.addAttribute("locations", locations);
		User currentUser = userServ.findUser((Long) session.getAttribute("user_id"));
		model.addAttribute("currentUser", currentUser);

		// events in user location..
		List<Event> notJoinedEventsInLocation = eventsInUserLocation(eventServ.notJoindEventByCurrentUser(currentUser),
				currentUser);
		List<Event> userEventsInLocation = eventsInUserLocation(currentUser.getCreatedEvents(), currentUser);
		List<Event> userJoinedEventInLocation = eventsInUserLocation(currentUser.getJoinedEvents(), currentUser);

		model.addAttribute("notJoinedEventsInLocation", notJoinedEventsInLocation);
		model.addAttribute("userEventsInLocation", userEventsInLocation);
		model.addAttribute("userJoinedEventInLocation", userJoinedEventInLocation);

		// events in other location..
		List<Event> notJoinedEvents= eventsInOtherLocations(eventServ.notJoindEventByCurrentUser(currentUser),
				currentUser);
		List<Event> userEvents = eventsInOtherLocations(currentUser.getCreatedEvents(), currentUser);
		List<Event> userJoinedEvent = eventsInOtherLocations(currentUser.getJoinedEvents(), currentUser);

		model.addAttribute("notJoinedEvents", notJoinedEvents);
		model.addAttribute("userEvents", userEvents);
		model.addAttribute("userJoinedEvent", userJoinedEvent);

		return "events.jsp";
	}

	// 2
	@PostMapping("/new")
	public String createEvent(@Valid @ModelAttribute("event") Event event, BindingResult result,
			RedirectAttributes redirectAttributes, HttpSession session) {

		Date date = new Date();
		if (event.getDate().before(date)) {
			System.out.println("date not valid \n \n");
			result.rejectValue("date", "Invalid", "date should be in the future!");
		}
		if (result.hasErrors()) {
			System.out.println("errors in creatingg event \n \n");
			redirectAttributes.addFlashAttribute("event", event);
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.event", result);
			redirectAttributes.addFlashAttribute("danger", "creating event failed.");
			return "redirect:/events";
		}
		System.out.println("no errors \n \n");
		eventServ.createEvent(event, (Long) session.getAttribute("user_id"));
		return "redirect:/events";

	}

	// 3
	@GetMapping("/{id}/edit")
	public String editEvent(@PathVariable("id") Long id, @Valid @ModelAttribute("event") Event event,
			BindingResult result, RedirectAttributes redirectAttributes, HttpSession session, Model model) {

		if (checkUserLogged(session, redirectAttributes)) {
			return "redirect:/";
		}

		Event foundEvent = eventServ.findEvent(id);
		model.addAttribute("event", foundEvent);
		model.addAttribute("eventId", id);
		model.addAttribute("locations", locations);
		return "edit.jsp";
	}

	// 3
	@PutMapping("/{id}/edit")
	public String updateEvent(@PathVariable("id") Long id, @Valid @ModelAttribute("event") Event event,
			BindingResult result, RedirectAttributes redirectAttributes, HttpSession session, Model model) {

		Date date = new Date();
		if (event.getDate().before(date)) {
			System.out.println("date not valid \n \n");
			result.rejectValue("date", "Invalid", "date should be in the future!");
		}

		if (result.hasErrors()) {
			System.out.println("errors in updating event \n \n");
			redirectAttributes.addFlashAttribute("event", event);
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.event", result);
			redirectAttributes.addFlashAttribute("danger", "updating event failed.");
			return "redirect:/events/{id}/edit";
		}
		eventServ.updateEvent(id, event);
		redirectAttributes.addFlashAttribute("success", "updating event successed.");
		return "redirect:/events";
	}

	// 4
	@GetMapping("/{id}/delete")
	public String deleteEvent(@PathVariable("id") Long id) {
		eventServ.deleteEvent(id);
		return "redirect:/events";
	}

	// 5
	@GetMapping("/{id}/join")
	public String joining(@PathVariable("id") Long id, HttpSession session, RedirectAttributes redirectAttributes) {

		User user = userServ.findUser((Long) session.getAttribute("user_id"));
		System.out.println("inside join: \n \n"+ user);
		if (!eventServ.joinEvent(id, user)) {
			redirectAttributes.addFlashAttribute("danger", "canceling event failed event failed.");
		}
		return "redirect:/events";
	}
	


	// 6
	@GetMapping("/{id}/cancel")
	public String notJoining(@PathVariable("id") Long id, HttpSession session, RedirectAttributes redirectAttributes) {
		User user = userServ.findUser((Long) session.getAttribute("user_id"));
		if (!eventServ.notJoinEvent(id, user)) {
			redirectAttributes.addFlashAttribute("danger", "joining event failed event failed.");
		}
		return "redirect:/events";
	}

	// 8
	@GetMapping("/{id}")
	public String viewEvent(@PathVariable("id") Long id, Model model, HttpSession session,@Valid @ModelAttribute("comment") Comment comment,
			BindingResult result,RedirectAttributes redirectAttributes) {
		model.addAttribute("event", eventServ.findEvent(id));
		return "view_event.jsp";
	}

	// -------------------------------------------------------------------------------------------
	// helper methods..

	private boolean checkUserLogged(HttpSession session, RedirectAttributes redirectAttributes) {
		if (session.getAttribute("user_id") == null) {
			redirectAttributes.addFlashAttribute("danger", "You should login first.");
			return true;
		}
		return false;
	}

	private List<Event> eventsInUserLocation(List<Event> searchEvents, User currentUser) {
		List<Event> events = new ArrayList<>();
		for (Event event : searchEvents) {
			if (event.getLocation().equals(currentUser.getLocation())) {
				events.add(event);
			}
		}
		return events;
	}

	private List<Event> eventsInOtherLocations(List<Event> searchEvents, User currentUser) {
		List<Event> events = new ArrayList<>();
		for (Event event : searchEvents) {
			if (!event.getLocation().equals(currentUser.getLocation())) {
				events.add(event);
			}
		}
		return events;
	}
}

//Event eventlocation = eventServ.findAll().get(0);
//System.out.println(
//		"is event location == user location:" + eventlocation.getLocation() + currentUser.getLocation());
//System.out.println(
//		"is event location == user location:" + eventlocation.getLocation().equals(currentUser.getLocation()));
