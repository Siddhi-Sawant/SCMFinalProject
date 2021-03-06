package com.springboot6772.serviceimplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot6772.entity.Admin;
import com.springboot6772.entity.Contact;
import com.springboot6772.entity.User;
import com.springboot6772.repository.AdminRepo;
import com.springboot6772.service.AdminService;

@Service
public class AdminServiceImplementation implements AdminService
{
	@Autowired
  AdminRepo adminRepo;
	@Override
	public void addAdmin(Admin admin) 
	{
		admin.setAdminId(1);
		admin.setAdminName("Admin");
		admin.setPassword("Admin111");
		this.adminRepo.save(admin);
		
	}
	@Override
	public Admin checkLogin(String adminName, String password) 
	{
		
		return adminRepo.findByadminNameAndPassword(adminName, password);
	}
	@Override
	public Page<Contact> getAllContacts(int userId, Pageable pageable) 
	{
		// TODO Auto-generated method stub
		return this.adminRepo.findContactByUser(userId, pageable);
	}
	
	

}
