package com.springboot6772.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.io.File;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot6772.entity.Contact;
import com.springboot6772.entity.User;
import com.springboot6772.repository.ContactRepo;
import com.springboot6772.repository.UserRepo;
import com.springboot6772.service.ContactService;
import com.springboot6772.service.userService;

import java.util.*;

@Controller
@ControllerAdvice

public class UserController 
{
	@Autowired
	userService userservice;

	@Autowired
	ContactService contactservice;

	@Autowired
	UserRepo userRepo;
	
	@Autowired
   ContactRepo contactRepo;
	
	@GetMapping("/home")
	public String home(Model m)
	{
		m.addAttribute("title", "Smart Contact Manager");
		return "user/home";
	}

	/*
	 * @ModelAttribute public void addCommonData(User user,Model m) { String
	 * userName=user.getUserName(); System.out.println("USERNAME "+userName);
	 * 
	 * User user1=userservice.getUserByUserName(userName);
	 * System.out.println("USER "+user1); m.addAttribute("user1", user1); }
	 */

	@GetMapping("/addcontact")
	public String addContact(Model model) 
	{
		model.addAttribute("title","Smart Contact Manager");
		model.addAttribute("contact", new Contact());
		return  "user/addcontact"; }

	@PostMapping("/add_contact{userId}")
	//@PostMapping("/add_contact")
	public String add_contact(@ModelAttribute Contact contact,@PathVariable("userId") Integer userId,
			@RequestParam("person_Image")  MultipartFile file,Model model,HttpSession session)throws IOException
	{
		
		User user1=this.userservice.getUserByUserId(userId);
		try 
		{
			
			if(file.isEmpty())
			{

				System.out.println("File is empty");
				contact.setPersonImage("default.png");
				System.out.println("-----------------------------message 1");
			}
			else
			{
				System.out.println("-----------------------------message 2");
				contact.setPersonImage(file.getOriginalFilename());
				File saveFile= new ClassPathResource("static/img").getFile();
				Path path=Paths.get(saveFile.getAbsolutePath()+File.separator+file.getOriginalFilename());
				Files.copy(file.getInputStream(), path,StandardCopyOption.REPLACE_EXISTING);
				System.out.println("File is uploaded");
			}
			user1.getContacts().add(contact);
			
			contact.setUsers(user1);
			this.contactservice.addContact(contact);
					
		
			session.setAttribute("message", new Message("Contact added successfully","alert-success"));
			
		}
		catch(Exception e)
		{
           System.out.println(e);
		}
		
		return "redirect:/viewContacts{userId}";
	}
	

	@GetMapping("/viewContacts{userId}")
	public String viewContacts(@PathVariable("userId") int userId,Model model)
	{
		
//		List<Contact> contactDetails=this.contactservice.getContactByUserId(userId);
//		
		//model.addAttribute("listContact",contactDetails);
//		
		//return "user/ViewContacts";
		return viewPagination(1,model);
	}
	
//	@GetMapping("/viewContacts{userId}/{pageNo}/{pageSize}")
//	public List<Contact> getPagination(@PathVariable("userId") int userId,@PathVariable int pageNo,@PathVariable int pageSize)
//	{
//		return contactservice.getContactByUserId(userId, pageSize, pageNo);
//	}

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public String handleFileUploadError(RedirectAttributes ra,HttpSession session)
	{
		System.out.println("Caught file upload error");
		ra.addFlashAttribute("alert-danger", "File size is greater than 1MB");
		
		session.setAttribute("message", new Message("File size is greater than 1MB","alert-danger"));
		//session.setAttribute("message3", new Message("File size is greater than 1MB","alert-danger"));
		return "user/addcontact";
		
	}
	
	@GetMapping("/editContact{contactId}")
	public String updateContact(Model  model,@PathVariable("contactId") int contactId)
	{
		model.addAttribute("title", "User Dashboard");
		Contact contact=contactservice.getContactByContactId(contactId);
		model.addAttribute("contact", contact);
		return "user/editContact";
	}
    
	@PostMapping("/saveUpdateContact{contactId}")
	public String update_Contact( @ModelAttribute Contact contact,@PathVariable("contactId") int contactId,HttpSession session,@RequestParam("person_Image") MultipartFile file) throws IOException
	{
		if(file.isEmpty())
		{

			System.out.println("File is empty");
			System.out.println("-----------------------------message 1");
		}
		else
		{
			System.out.println("-----------------------------message 2");
			contact.setPersonImage(file.getOriginalFilename());
			File saveFile= new ClassPathResource("static/img").getFile();
			Path path=Paths.get(saveFile.getAbsolutePath()+File.separator+file.getOriginalFilename());
			Files.copy(file.getInputStream(), path,StandardCopyOption.REPLACE_EXISTING);
			System.out.println("File is uploaded");
		}
		
		this.contactservice.updateContact(contact, contactId);
		session.setAttribute("message1", new Message("Contact Updated successfully","alert-success"));
		
		return "redirect:/editContact{contactId}";
	}
	
//	@GetMapping("/deleteContact{contactId}")
//	public String deleteContact(@PathVariable("contactId") int contactId)
//	{
//		System.out.println("ContactID------------------------------------------"+contactId);
//		//System.out.println("UserId---------------------------"+userId);
//		contactservice.deleteContactByContactId(contactId);
//		return "redirect:/user/ViewContacts";
//	}
	
	 @GetMapping("/deleteContact")
	//@GetMapping("/viewContacts{userId}")
	public String deleteContact(@RequestParam("contactId") Integer contactId, Model model)
	{
	   int userId= this.contactservice.getContactByContactId(contactId).getUsers().getUserId();
	   
	   System.out.println("User ID +++++++++++++++++++  "+userId);
	   
		contactservice.deleteContactByContactId(contactId);
		//return "redirect:/user/ViewContacts";
		//return "redirect:/viewContacts/{userId}";
		List<Contact> contactDetails=this.contactservice.getContactByUserId(userId);
		
		//Pageable pageable=PageRequest.of(page, 4);
		//Page<Contact> contactDetails=this.contactRepo.getContactByUserId(userId, pageable);
		model.addAttribute("contactObj",contactDetails);
		return "user/ViewContacts";
		//return "redirect:/viewContacts{userId}";
	}
	
	@GetMapping("viewProfile")
	public String viewProfile()
	{
		return "user/viewProfile";
	}
	  
	@GetMapping("/profile")
	public String uploadUserProfile(Model model)
	{
		model.addAttribute("title", "Upload Profile");
		return "user/uploadProfile";
	}
	
	@PostMapping("/uploaded{userId}")
	public String Upload_profile(@RequestParam("profile_image")MultipartFile file,@PathVariable("userId")Integer userId,HttpSession session) throws IOException
	{
		User user1=this.userservice.getUserByUserId(userId);
		if(file.isEmpty())
		{

			System.out.println("File is empty");
			user1.setProfileImage("default.png");
			System.out.println("-----------------------------message 1");
		}
		else
		{
			System.out.println("-----------------------------message 2");
			
			user1.setProfileImage(file.getOriginalFilename());
			File saveFile= new ClassPathResource("static/img").getFile();
			Path path=Paths.get(saveFile.getAbsolutePath()+File.separator+file.getOriginalFilename());
			Files.copy(file.getInputStream(), path,StandardCopyOption.REPLACE_EXISTING);
			System.out.println("File is uploaded");
			session.setAttribute("message3", new Message("Profile Image uploaded successfully...","alert-warning"));
			}
		
		this.userservice.addUser(user1);
		return "user/uploadProfile";
	}
	
	//pagination code
	
	@GetMapping("/page/{pageNo}")
	public String viewPagination(@PathVariable(value="pageNo") int pageNo, Model model)
	{
        
		///List<Contact>listContact1=this.contactservice.getContactByUserId(userId);
		
		//model.addAttribute("listContact",contactDetails);
		//User user1=this.userservice.getUserByUserId(userId);
	    
		int pageSize=5;
		Page<Contact> page=contactservice.findPagination(pageNo, pageSize);
		List<Contact>listContact=page.getContent();
		model.addAttribute("currentpage", pageNo);
		model.addAttribute("totalpages", page.getTotalPages());
		model.addAttribute("totalitems", page.getTotalElements());
		model.addAttribute("listContact", listContact);
		//model.addAttribute("listcontact", listContact1);
		return "user/ViewContacts";
		
		
		
		
	}
	
	

}

