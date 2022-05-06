package com.springboot6772.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot6772.entity.Contact;

public interface ContactRepo extends JpaRepository<Contact, Integer>
{

}
