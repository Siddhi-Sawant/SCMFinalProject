package com.springboot6772.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot6772.entity.Admin;

public interface AdminRepo extends JpaRepository<Admin, Integer>
{
  public Admin findByadminNameAndPassword(String adminName,String password);
}
