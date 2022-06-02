package com.springboot6772.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Contact 
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int contactId;
  
  @Column
  private String personName;
  
  @Column
  private String nickName;
   
 
  @Column
  private String mobileNo;
  
  @Column
  private String personEmail;
  
  @Column
  private String personWork;
  
  @Column
  private String personDescription;
  
  @Column
  private String personImage;
  
  @ManyToOne
   private User users;

public int getContactId() {
	return contactId;
}

public void setContactId(int contactId) {
	this.contactId = contactId;
}

public String getPersonName() {
	return personName;
}

public void setPersonName(String personName) {
	this.personName = personName;
}

public String getNickName() {
	return nickName;
}

public void setNickName(String nickName) {
	this.nickName = nickName;
}

public String getMobileNo() {
	return mobileNo;
}

public void setMobileNo(String mobileNo) {
	this.mobileNo = mobileNo;
}

public String getPersonEmail() {
	return personEmail;
}

public void setPersonEmail(String personEmail) {
	this.personEmail = personEmail;
}

public String getPersonWork() {
	return personWork;
}

public void setPersonWork(String personWork) {
	this.personWork = personWork;
}

public String getPersonDescription() {
	return personDescription;
}

public void setPersonDescription(String personDescription) {
	this.personDescription = personDescription;
}

public String getPersonImage() {
	return personImage;
}

public void setPersonImage(String personImage) {
	this.personImage = personImage;
}

public User getUsers() {
	return users;
}

public void setUsers(User users) {
	this.users = users;
}

public Contact(int contactId, String personName, String nickName, String mobileNo, String personEmail,
		String personWork, String personDescription, String personImage, User users) {
	super();
	this.contactId = contactId;
	this.personName = personName;
	this.nickName = nickName;
	this.mobileNo = mobileNo;
	this.personEmail = personEmail;
	this.personWork = personWork;
	this.personDescription = personDescription;
	this.personImage = personImage;
	this.users = users;
}

public Contact() {
	super();
	// TODO Auto-generated constructor stub
}
  
  
  
}
