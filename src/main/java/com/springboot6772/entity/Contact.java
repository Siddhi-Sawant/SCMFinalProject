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
@Getter
@Setter
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
}
