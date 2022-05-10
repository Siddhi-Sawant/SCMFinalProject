package com.springboot6772.service;

import java.util.List;

import com.springboot6772.controller.NormalController;
import com.springboot6772.entity.Contact;
import com.springboot6772.entity.User;

public interface userService {

	 public void addUser(User user);
	
	public User checkLogin(String userName,String password);
	
	public List<User> getUsers();
	
	public User getUserByUserId(int userId);
	
	
	

}
