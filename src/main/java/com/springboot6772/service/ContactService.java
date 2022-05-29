package com.springboot6772.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.springboot6772.entity.Contact;
import com.springboot6772.entity.User;

public interface ContactService 
{
  public void addContact(Contact contact);
  
  public List<Contact> getAllContacts(int userId,String keyword,Pageable pageable);
  
  
   public List<Contact>  getContactByUserId(int userId);
   
 
  
  //update contact
  public Contact getContactByContactId(int contactId);
  
  public void updateContact(Contact contact,int contactId);

  
  //delete contact
   public void deleteContactByContactId(int contactId);
  
 
   
   
   //Pagination1
   public Page<Contact> getAllContactsByUserId( int userId ,Pageable pageable);
   
   //search
   public Contact checkPersonName(String personName);
   
  }
