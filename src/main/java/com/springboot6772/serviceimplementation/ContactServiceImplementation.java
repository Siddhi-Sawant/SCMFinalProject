package com.springboot6772.serviceimplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot6772.entity.Contact;
import com.springboot6772.repository.ContactRepo;
import com.springboot6772.service.ContactService;

@Service
public class ContactServiceImplementation implements ContactService
{
	@Autowired
	ContactRepo contactRepo;

	@Override
	public void addContact(Contact contact) 
	{
		this.contactRepo.save(contact);
		
	}

	@Override
	public List<Contact> getAllContacts()
	{
		
		return contactRepo.findAll();
	}
	
	

}
