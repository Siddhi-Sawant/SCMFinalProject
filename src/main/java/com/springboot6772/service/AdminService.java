package com.springboot6772.service;



import com.springboot6772.entity.Admin;



public interface AdminService 
{
  public void addAdmin(Admin admin);
  
  public Admin checkLogin(String adminName, String password);
  

}
