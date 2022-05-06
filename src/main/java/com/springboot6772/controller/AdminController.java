package com.springboot6772.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot6772.entity.Admin;
import com.springboot6772.entity.Contact;
import com.springboot6772.entity.User;
import com.springboot6772.service.AdminService;
import com.springboot6772.service.userService;

@Controller
public class AdminController 
{
	@Autowired
  AdminService adminService;
	
	@Autowired
	userService userservice;
	@RequestMapping("/admin")
	public String adminhome()
	{
		return "admin/adminhome";
	}
	
	@GetMapping("/adminsignup")
	public String adminsignup(Model model)
	{
		model.addAttribute("admin", new Admin());
		return "admin/adminsignup";
	}
	
	
	
	@PostMapping("/adminregister")
	public String admin_signup(Model model,@ModelAttribute Admin admin)
	{
		this.adminService.addAdmin(admin);
		return "admin/adminLogin";
	}
	
	@GetMapping("/adminLogin")
	public String adminlogin()
	{
		return "admin/adminLogin";
	}
	
	@PostMapping("/checkLogin1")
	public String adminlogin1(@ModelAttribute Admin admin,Model model,HttpSession session)
	{
		Admin admin1=adminService.checkLogin(admin.getAdminName(), admin.getPassword());
		if(admin1!=null)
		{
			session.setAttribute("admin", admin);
			return "admin/dashhome";
		}
		else
		{
			session.setAttribute("message", new Message("Invalid Username and Password ","alert-danger"));
			return "admin/adminogin";
		}
//		
	}
	
	@GetMapping("/admindash")
	public String admindash(Model model)
	{
       model.addAttribute("title","Admin Dashboard");
       return "admin/admindashboard";
	}
	
	
	@GetMapping("/dashhome")
	public String dashhome(Model model)
	{
		 model.addAttribute("title","Admin Dashboard");
	       return "admin/dashhome";
	}
	
	 @GetMapping("/viewUser")
	 public String viewUser(Model model)
	 {
		 List<User> userDetails=this.userservice.getUsers();
		 model.addAttribute("userObj",userDetails);
		 return "admin/viewUser";
	 }
}
