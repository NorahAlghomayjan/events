package com.norah.events.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="events")
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="event name should be filled..")
	private String name;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date date;
	
	@NotBlank(message="event location should be filled.")
	private String location;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="host_id")
	private User host; // == User createdBy
	
	 @ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	 @JoinTable(name = "users_events", 
	            joinColumns = @JoinColumn(name = "event_id"), 
	            inverseJoinColumns = @JoinColumn(name = "user_id")
	        )
	 private List<User> joinedUsers;
	 
	 @OneToMany(mappedBy="event",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	 private List<Comment> comments;
	 
	 public Event() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Long getId() {
		return id;
	}

	public User getHost() {
		return host;
	}

	public void setHost(User host) {
		this.host = host;
	}

	public List<User> getJoinedUsers() {
		return joinedUsers;
	}

	public void setJoinedUsers(List<User> joinedUsers) {
		this.joinedUsers = joinedUsers;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}


	
	
	

}
