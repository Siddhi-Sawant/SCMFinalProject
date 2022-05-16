package com.springboot6772.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.Random;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot6772.entity.Admin;
import com.springboot6772.entity.User;
import com.springboot6772.service.AdminService;
import com.springboot6772.service.ContactService;
import com.springboot6772.service.EmailService;
import com.springboot6772.service.userService;

@Controller
public class NormalController 
{  
	Random random=new Random(1000);
	@Autowired
	userService userservice;
	
	@Autowired
	AdminService adminservice;
	
	@Autowired
	EmailService emailService;
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
		Admin admin=new Admin();
		admin.setAdminName("Admin");
		admin.setPassword("Admin");
		this.adminservice.addAdmin(admin);
		
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
		 
		 User user1= userservice.checkLogin(user.getUserEmail(),user.getPassword());
		
		 if(user1!=null)
		 {
			 session.setAttribute("user", user);
			 session.setAttribute("user1", user1);
			 session.setAttribute("userId", user1.getUserId());
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
	 
	 @PostMapping("/send_otp")
	 public String sendOtp(@RequestParam("userEmail") String userEmail,HttpSession session)
	 {
		
		 System.out.println("Email++++++++++++++++++++++++++++++++++++++"+userEmail);
		 int otp=random.nextInt(9999);
		 System.out.println("OTP========================================="+otp);	
		
		 //otp sent to email
		 
		String subject="OTP From SCM";
		String message="<h1> OTP = "+otp+"</h1>";
		String to=userEmail;
		String from="aratij19799@gmail.com";
		boolean flag =this.emailService.sendEmail(subject, message,to,from);
		 
		if(flag)
		{
			session.setAttribute("newotp", otp);
			session.setAttribute("userEmail", userEmail);
			return "verify_otp";
		}
		else
		{
			session.setAttribute("message5",new Message("Check your email Id","alert-warning"));
		    return "forgotpass";
		}
	 }
	
	 //verify otp
	 
	 public String verifyOtp()
	 {
		 return "";
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
				user.setProfileImage("default.png");
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
