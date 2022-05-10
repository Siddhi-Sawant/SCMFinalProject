package com.springboot6772.serviceimplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot6772.entity.Contact;
import com.springboot6772.entity.User;
import com.springboot6772.repository.ContactRepo;
import com.springboot6772.repository.UserRepo;
import com.springboot6772.service.ContactService;

@Service
public class ContactServiceImplementation implements ContactService
{
	@Autowired
	ContactRepo contactRepo;
	
	@Autowired
	UserRepo userRepo;

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

	@Override
	public List<Contact> getContactByUserId(int userId) {
		// TODO Auto-generated method stub
		
	   User  user=  this.userRepo.findById(userId).get();
	   
	   return user.getContacts();
	}

	@Override
	public Contact getContactByContactId(int contactId) 
	{
		Contact contact=contactRepo.findById(contactId).get();
		return contact;
	}

	@Override
	public void updateContact(Contact contact, int contactId) 
	{
		Contact contactObj=contactRepo.findById(contactId).get();
		contactObj.setPersonName(contact.getPersonName());
		contactObj.setNickName(contact.getNickName());
		contactObj.setMobileNo(contact.getMobileNo());
		contactObj.setPersonEmail(contact.getPersonEmail());
		contactObj.setPersonWork(contact.getPersonWork());
		contactObj.setPersonImage(contact.getPersonImage());
		contactObj.setPersonDescription(contact.getPersonDescription());
		this.contactRepo.save(contactObj);
		
		
	}

	@Override
	public void deleteContactByContactId(int contactId) 
	{
		System.out.println("contactIdserviceDelete---------------"+contactId);
		Contact contact=contactRepo.findById(contactId).get();
		contact.getUsers().getContacts().remove(contact);
		updateContact(contact, contactId);
		this.contactRepo.delete(contact);
		
	}
	
	

}
