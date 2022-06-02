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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserContact() {
		return userContact;
	}

	public void setUserContact(String userContact) {
		this.userContact = userContact;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public User(int userId,
			@Size(min = 4, max = 12, message = "minimum character must be 4 and maximum character should be 12") @NotBlank(message = "please enter student name") String userName,
			@NotBlank(message = "please enter correct email address") String userEmail,
			@Size(min = 10, max = 10, message = "Contact number is incorrect") @NotBlank(message = "please enter student contact number") String userContact,
			@Size(min = 8, max = 25, message = "At least 1 uppercase letter 1 lowercase letter 1 special symbol 1 number") @NotBlank(message = "please enter password") String password,
			String profileImage, List<Contact> contacts) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userContact = userContact;
		this.password = password;
		this.profileImage = profileImage;
		this.contacts = contacts;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	 
	 
	 
}
