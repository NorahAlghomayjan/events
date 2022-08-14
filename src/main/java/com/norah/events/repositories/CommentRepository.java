package com.norah.events.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.norah.events.models.Comment;
import com.norah.events.models.Event;

@Repository
public interface CommentRepository extends CrudRepository<Comment,Long> {
	
	List<Comment> findAll();
	List<Comment> findAllByEvent(Event event);
}
