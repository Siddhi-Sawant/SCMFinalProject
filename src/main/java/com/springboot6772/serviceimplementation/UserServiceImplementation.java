package com.springboot6772.serviceimplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot6772.entity.Contact;
import com.springboot6772.entity.User;
import com.springboot6772.repository.ContactRepo;
import com.springboot6772.repository.UserRepo;
import com.springboot6772.service.userService;
@Service
public class UserServiceImplementation implements userService 
{
	 @Autowired
     UserRepo userRepo;

	
     
     @Override
     public void addUser(User user)
     {
    	this.userRepo.save(user);
     }

	@Override
	public User checkLogin(String userName, String password) {
		
		return userRepo.findByuserNameAndPassword(userName,password);
	}

	@Override
	public List<User> getUsers() 
	{
		
		return this.userRepo.findAll();
	}

	@Override
	public User getUserByUserId(int userId) 
	{
		
		return this.userRepo.findById(userId).get();
	}
    
     
}
