package com.norah.events.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.norah.events.models.Comment;
import com.norah.events.models.Event;
import com.norah.events.repositories.CommentRepository;

@Service
public class CommentService {

	private final EventService eventServ;
	private final UserService userServ;
	private final CommentRepository commentRepo;
	public CommentService(EventService eventServ, UserService userServ, CommentRepository commentRepo) {
		super();
		this.eventServ = eventServ;
		this.userServ = userServ;
		this.commentRepo = commentRepo;
	}
	
	
	List<Comment> findAllCommentByEvent(Long eventId){
		Event event = eventServ.findEvent(eventId);
		return commentRepo.findAllByEvent(event);
	}
}
