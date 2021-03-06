package com.springboot6772.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springboot6772.entity.Admin;
import com.springboot6772.entity.Contact;
import com.springboot6772.entity.User;

public interface AdminRepo extends JpaRepository<Admin, Integer>
{
  public Admin findByadminNameAndPassword(String adminName,String password);
  
  @Query("from Contact c where c.users.userId=:userId")
	public Page<Contact> findContactByUser(@Param("userId") int userId,Pageable pageable);
  
}
