package com.springboot6772.serviceimplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
	public List<Contact> getAllContacts(int userId,String keyword)
	{
		 User  user=  this.userRepo.findById(userId).get();
	  
		  if(keyword !=null)
		  {
		     return contactRepo.findAll(keyword);  
		  
		  }
		  
		  
	    return user.getContacts();
	  
	}

	@Override
	public List<Contact> getContactByUserId(int userId)
	{
		
	   User  user=  this.userRepo.findById(userId).get();
	   
	   
	  return   user.getContacts();
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
 
	//pagination
	@Override
	public Page<Contact> findPagination(int pageNo, int pageSize,int userId)
	{
	  
       Pageable pageable=PageRequest.of(pageNo-1, pageSize);
	  // return this.contactRepo.findAll(pageable);
       return this.contactRepo.findContactByUser(userId, pageable);
       //return user.getContacts();
	}

	@Override
	public Page<Contact> getAllContactsByUserId(int userId,Pageable pageable)
	{
		//User user=this.userRepo.findById(userId).get();
		
		 return  contactRepo.findContactByUser(userId, pageable) ;
		//return contactRepo.findAll(pageable);
	}

	
	
	

}
