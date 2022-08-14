package com.norah.events.services;


import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.norah.events.models.User;
import com.norah.events.models.requests.UserLogin;
import com.norah.events.repositories.UserRepository;



@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepo;
    
    
    
    // 1. registeration..
    public User register(User newUser, BindingResult result) {
    	// newUser is an obj that is going to be stored in db.
    	
    	// 1. fetch if the email matched already existing one db
        if(userRepo.findByEmail(newUser.getEmail()).isPresent()) {
        	// exist -> add error
            result.rejectValue("email", "Unique", "This email is already in use!");
            
        }
        
        //2. compare the password == confirm
        if(!newUser.getPassword().equals(newUser.getConfirm())) {
        	// equal -> add error
            result.rejectValue("confirm", "Matches", "The Confirm Password must match Password!");
        }
        
        
        //3. check validations (username > 3 chars, password > 8 chrs, email == email@email.com )
        if(result.hasErrors()) {
            return null;
        } else {
        	//4. no errors..
            String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
            newUser.setPassword(hashed);
            return userRepo.save(newUser);
        }
    }
    
    
    
    //2. login
    public User login(UserLogin newLogin, BindingResult result) {
    	//1. check validation
        if(result.hasErrors()) {
            return null;
        }
        //2. return the user from db using email.
        Optional<User> potentialUser = userRepo.findByEmail(newLogin.getEmail());
        if(!potentialUser.isPresent()) {
        	//3. not exist -> add error.
            result.rejectValue("email", "Unique", "Unknown email!");
            return null;
        }
        //4. exist -> return the user.
        User user = potentialUser.get();
        
        //5. compare the returned user pass == the inserted password
        if(!BCrypt.checkpw(newLogin.getPassword(), user.getPassword())) {
        	//6. doesn't match -> add errors.
            result.rejectValue("password", "Matches", "Invalid Password!");
        }
        //7. check validation again.
        if(result.hasErrors()) {
            return null;
        } else {
        	// success
            return user;
        }
    }
    
    public User findUser(Long id) {
    	Optional<User> foundUser = userRepo.findById(id);
    	if(foundUser.isPresent()) {
    		return foundUser.get();
    	}else {
    		return null;
    		
    	}
    }
    
    public User saveChanges(User user) {
    	
    	return userRepo.save(user);
    }
    
    
    
}
