package com.springboot6772.service;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.springboot6772.entity.Admin;
import com.springboot6772.entity.Contact;
import com.springboot6772.entity.User;



public interface AdminService 
{
  public void addAdmin(Admin admin);
  
  public Admin checkLogin(String adminName, String password);
  
  //Pagination1
  public Page<Contact> getAllContacts(int userId ,Pageable pageable);
  
}
