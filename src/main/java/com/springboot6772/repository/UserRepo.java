package com.springboot6772.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springboot6772.entity.Contact;
import com.springboot6772.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>
{
   public User findByuserEmailAndPassword(String userEmail,String password);
	
   public User getUserByUserName(String userName);
   
   //check valid email
   
  public User findByuserEmail(String userEmail);
  
  
  
//  @Query("from User user where user.contacts.personName=:personName")
// 	public List<User> findByNameContainingAndUser(@Param("personName")String personName);

}




