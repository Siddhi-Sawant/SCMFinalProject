package com.springboot6772.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User 
{
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int userId;
	 
	 @Column(length = 30,nullable = false)
	
	 @Size(min=4,max=12, message="minimum character must be 4 and maximum character should be 12")
	 @NotBlank(message="please enter student name")
     private String userName;
	 
	 @Column(length = 30,nullable = false)
	 @NotBlank(message="please enter correct email address")
     private String userEmail;
	 
	 @Column(length = 10,nullable = false)
	 @Size(min=10,max=10 ,message="Contact number is incorrect")
	 @NotBlank(message="please enter student contact number")
     private String userContact;
	 
	 @Column(length = 30,nullable = false)
	 @Size(min=8,max=25,message="At least 1 uppercase letter 1 lowercase letter 1 special symbol 1 number")
	 @NotBlank(message="please enter password")
     private String password;
	 
	 @Column
	 private String profileImage;
	 
	 @OneToMany(cascade = CascadeType.ALL)
	 private  List <Contact>contacts;
}
