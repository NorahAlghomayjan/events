package com.norah.events.models;


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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

    
@Entity
@Table(name="users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Size(min=2, max=30, message="first name must be between 2 and 30 characters")
    private String firstName;
    
    @Size(min=2, max=30, message="first name must be between 2 and 30 characters")
    private String lastName;
    
    @Size(min=2, max=30,message="insert location")
    private String location;
    
    @NotBlank(message="Email is required!")
    @Email(message="Please enter a valid email!")
    private String email;
    
    @NotBlank(message="Password is required!")
    @Size(min=4, max=128, message="Password must be between 4 and 128 characters")
    private String password;
    
    @Transient
    @NotBlank(message="Confirm Password is required!")
    @Size(min=4, max=128, message="Confirm Password must be between 4 and 128 characters")
    private String confirm;
    
    @OneToMany(mappedBy="host",fetch=FetchType.LAZY)
    private List<Event> createdEvents;
    
    @ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
    @JoinTable(
            name = "users_events", 
            joinColumns = @JoinColumn(name = "user_id"), 
            inverseJoinColumns = @JoinColumn(name = "event_id")
        )
    private List<Event> joinedEvents;

    @OneToMany(mappedBy="posted_by",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	 private List<Comment> comments;
  
    public User() {}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getConfirm() {
		return confirm;
	}


	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}


	public Long getId() {
		return id;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public List<Event> getCreatedEvents() {
		return createdEvents;
	}


	public void setCreatedEvents(List<Event> createdEvents) {
		this.createdEvents = createdEvents;
	}


	public List<Event> getJoinedEvents() {
		return joinedEvents;
	}


	public void setJoinedEvents(List<Event> joinedEvents) {
		this.joinedEvents = joinedEvents;
	}


	public List<Comment> getComments() {
		return comments;
	}


	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
    
    


	
	
      
}
    
