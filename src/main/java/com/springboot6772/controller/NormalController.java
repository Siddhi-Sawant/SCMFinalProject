package com.springboot6772.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot6772.entity.User;
import com.springboot6772.service.ContactService;
import com.springboot6772.service.userService;

@Controller
public class NormalController 
{ 
	@Autowired
	userService userservice;
	
	
	
	/*
	 * @GetMapping("/home") public String home() {
	 * 
	 * Student student = new Student();
	 * 
	 * // student.setStudentName("Pratiksha"); //
	 * student.setStudentEmail("pratikshadome@gmail.com");
	 * student.setStudentContact("7972298405");
	 * 
	 * this.studentService.addStudent(student);
	 * 
	 * return "home"; }
	 */
	@GetMapping("/")
	public String home(Model model)
	{
		model.addAttribute("title","Smart Contact Manager");
		return "home";
	}
	 @GetMapping("/signup")
	 public String signup(Model model)
	 {
		 model.addAttribute("title","Smart Contact Manager");
		 model.addAttribute("user", new User());
		 return "signup";
	 }
	
	  
	 
	 @GetMapping("/login")
	 public String login(Model model)
	 {
		 model.addAttribute("title","Login Page");
		 return "login";
	 }
	 
	 
	 @PostMapping("/checkLogin")
	 public String checkLogin(@ModelAttribute User user, Model model,HttpSession session)
	 {
		 
		 User user1= userservice.checkLogin(user.getUserName(),user.getPassword());
		
		 if(user1!=null)
		 {
			 session.setAttribute("user", user);
			 session.setAttribute("user1", user1);
			 return "user/home";
		 }
		 else
		 {
	      session.setAttribute("message2", new Message("Invalid username and password !","alert-danger"));
		 return "login";
		 }
		 
		
		
	 }
	 @GetMapping("/pass")
	 public String forgotpass()
	 {
		 return "forgotpass";
	 }
	
	@PostMapping("/do_register")
	 public String register(@Valid @ModelAttribute User user,BindingResult result, @RequestParam(value="agreement",defaultValue ="false")boolean agreement,Model model,  HttpSession session)
	 {
		try 
		{
			 if(result.hasErrors())
				{
					model.addAttribute("user",user);
					return "signup";
				}
			
		    if(agreement)
			 {
		    	
				session.setAttribute("message1", new Message("User Is Registered Successfully !","alert-success"));
				this.userservice.addUser(user);
			
				return "login";
				
			 }
			 else
			 {
				 session.setAttribute("message1", new Message("User Is Not Registered !","alert-danger"));
				 throw new Exception("kindly accept the term and conditions!");
				
			 }
		    
		   
		}
		catch (Exception e) 
		{
			System.out.println(e);
		}
		
		
		 return "signup";
		 
	 }
	
//	@ModelAttribute
//	public void addCommonData(Model m,Principal principal)
//	{
//		String userName=;
//		System.out.println("USERNAME "+userName);
//	 	
//		User user=userservice.getUserByUserName(userName);
//		System.out.println("USER "+user);
//		m.addAttribute("user1", user);
//	}
//	
//	
//	@GetMapping("/addcontact")
//	public String addContact(Model model)
//	{
//		model.addAttribute("title","Smart Contact Manager");
//		return "user/addcontact";
//	}
	
}
