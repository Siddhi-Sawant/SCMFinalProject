package com.springboot6772.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springboot6772.entity.Contact;

public interface ContactRepo extends JpaRepository<Contact, Integer>
{
//	@Query("from Contact c where c.users.userId=:userId")
//	 public Page<Contact> getContactByUserId(@Param("userId")int userId,Pageable pageable);

}
