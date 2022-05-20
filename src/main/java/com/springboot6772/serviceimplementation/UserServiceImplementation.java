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
	public User checkLogin(String userEmail, String password) {
		
		return userRepo.findByuserEmailAndPassword(userEmail,password);
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

	@Override
	public User checkEmail(String userEmail)
	{
		
		return userRepo.findByuserEmail(userEmail);
	}

	@Override
	public void updatePassword(User user, int userId) 
	{
		User user1=this.userRepo.findById(userId).get();
		user1.setPassword(user.getPassword());
		this.userRepo.save(user1);
		
	}

	@Override
	public void removePhoto(String profileImage,int userId) 
	{
		User user1=this.userRepo.findById(userId).get();
		
		
	}
    
     
}
