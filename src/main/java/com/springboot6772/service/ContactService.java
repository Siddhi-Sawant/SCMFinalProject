package com.springboot6772.service;

import java.util.List;

import com.springboot6772.entity.Contact;

public interface ContactService 
{
  public void addContact(Contact contact);
  
  public List<Contact> getAllContacts();
}
