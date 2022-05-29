package com.springboot6772.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot6772.entity.Admin;
import com.springboot6772.entity.Contact;
import com.springboot6772.entity.User;
import com.springboot6772.repository.UserRepo;
import com.springboot6772.service.AdminService;
import com.springboot6772.service.ContactService;
import com.springboot6772.service.userService;

@Controller
public class AdminController 
{
	@Autowired
  AdminService adminService;
	
	@Autowired
	userService userservice;
	
	@Autowired
	ContactService contactservice;
	
	@Autowired
	UserRepo userRepo;
	
	@RequestMapping("/")
	public String adminhome()
	{
		
		return "admin/adminLogin";
	}
	
//	@GetMapping("/adminsignup")
//	public String adminsignup(Model model)
//	{
//		model.addAttribute("admin", new Admin());
//		return "admin/adminsignup";
//	}
//	
//	
//	
//	@PostMapping("/adminregister")
//	public String admin_signup(Model model,@ModelAttribute Admin admin)
//	{
//		this.adminService.addAdmin(admin);
//		return "admin/adminLogin";
//	}
//	
	@GetMapping("/admin")
	public String adminLogin(Model model)
	{
		
		model.addAttribute("title" ,"AdminLogin");
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
			return "/admin";
		}
		
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
	 
	 @GetMapping("/contact{userId}")
	 public String viewContact(Model model,@PathVariable("userId") Integer userId,HttpSession session)
	 {
//		 List<Contact> contactDetails=this.contactservice.getContactByUserId(userId);
//		 System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//		 System.out.println("AdminUserId------------------------------------------------===="+userId);
//		 model.addAttribute("contactObj",contactDetails); 
//		 return "admin/viewContact";
		 User user=this.userservice.getUserByUserId(userId);
		 model.addAttribute("user1", user);
		 return viewContacts1(model, 0, session,userId);
	 }
	 
	 @GetMapping("/contact/{pageNo}")
		public String viewContacts1(Model model,@PathVariable int pageNo,HttpSession session,@RequestParam("userId") int userId)
		{
			//int userId=(int) session.getAttribute("userId");
			System.out.println("USerId+++++++++++++++++++++++++++++++++++++++++++"+userId);
			User user1=this.userservice.getUserByUserId(userId);
			Pageable pageable=PageRequest.of(pageNo, 4);
			Page<Contact> page =this.adminService.getAllContacts(user1.getUserId(), pageable);
			//List<Contact> contactDetails=contactservice.getContactByUserId(userId);	
			model.addAttribute("contactObj", page);
			model.addAttribute("currentPage",pageNo);
			model.addAttribute("totalPages",page.getTotalPages());
			
		    //model.addAttribute("listContact",contactDetails);
			return "admin/viewContact";
		}
			
//			
	 
	 
	 @GetMapping("/delete{userId}")
	 public String deleteUser(@PathVariable("userId") int userId,HttpSession session)
	 {
		 User user1=userservice.getUserByUserId(userId);
		 this.userRepo.delete(user1);
		 session.setAttribute("message", new Message("User deleted Successfully","alert-success"));
		 return "redirect:/viewUser";
	 }
	 
	 @GetMapping("/showContactDetail{contactId}")
	   public String viewContactDetails11(@PathVariable("contactId")int contactId,HttpSession session,Model model)
	   { 
			//User user11=(User) session.getAttribute("user1");
		
			
			// Contact contact=(Contact) contactservice.getContactByUserId(user11.getUserId());
			Contact contact=this.contactservice.getContactByContactId(contactId);
			//contact.setPersonImage("default.png");
			model.addAttribute("contact1", contact);
			//session.setAttribute("contact1", contact);
			System.out.println("USer Id++++++++++++++++++++++++++++++++++++++++++++++++"+contact.getUsers().getUserId());
			System.out.println("PersonImage++++++++++++++++++++++++++++++++++++++++++++"+contact.getPersonImage());
			
			 User user=this.userservice.getUserByUserId(contact.getUsers().getUserId());
			 model.addAttribute("user1", user);
			
		   return "admin/ShowContactDetail";
			//return "user/viewProfile";
	   }
	 
	    @GetMapping("/Contact{contactId}")
		public String removeContact(@PathVariable("contactId") Integer contactId, Model model)
		{
		   int userId= this.contactservice.getContactByContactId(contactId).getUsers().getUserId();
		   
		   System.out.println("User ID +++++++++++++++++++  "+userId);
		   
			contactservice.deleteContactByContactId(contactId);
			
	        List<Contact> contactDetails=this.contactservice.getContactByUserId(userId);
			
			model.addAttribute("listContact",contactDetails);
			
			return "admin/ShowContactDetail";
			  //return "redirect:/viewContacts{userId}";
			
		}
		
}
