package com.springboot6772.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springboot6772.entity.Contact;
import com.springboot6772.entity.User;

public interface ContactRepo extends JpaRepository<Contact, Integer>
{
	//Pagination
	@Query("from Contact c where c.users.userId=:userId")
	public Page<Contact> findContactByUser(@Param("userId") int userId,Pageable pageable);
	
	//Search contact
	@Query("SELECT c FROM Contact c WHERE c.personName LIKE %?1% ")
	public List<Contact> findAll(String keyword);
   
	@Query("SELECT c FROM Contact c WHERE c.personName LIKE %?1%")
     public List<Contact> findByNameContainingAndUser(String personName,User user);
	
	
}
