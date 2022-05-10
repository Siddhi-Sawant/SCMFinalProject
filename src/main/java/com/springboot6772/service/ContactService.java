package com.springboot6772.service;

import java.util.List;

import com.springboot6772.entity.Contact;

public interface ContactService 
{
  public void addContact(Contact contact);
  
  public List<Contact> getAllContacts();
  
  public List<Contact>  getContactByUserId(int userId);
   
  
  //update contact
  public Contact getContactByContactId(int contactId);
  
  public void updateContact(Contact contact,int contactId);

  
  //delete contact
   public void deleteContactByContactId(int contactId);
  
  
}
