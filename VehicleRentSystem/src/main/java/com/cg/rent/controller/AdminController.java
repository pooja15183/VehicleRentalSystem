package com.cg.rent.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.rent.model.Login;
import com.cg.rent.model.User;
import com.cg.rent.service.AdminServiceImpl;
import com.cg.rent.service.UserManagementServiceImpl;


/**
 * 
 * @author sweet
 *
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
	
	private static final Logger logger = LogManager.getLogger(AdminController.class);
	
	@Autowired
	AdminServiceImpl adminService;
	
	@Autowired
	UserManagementServiceImpl userManager;
	
	
	
	/**
	 * adminLogin()
	 * To validate admin email and password.
	 * @param login
	 * @return String message
	 */
	@PostMapping("/login")
	String adminLogin(@RequestBody Login login)
	{
		String result = userManager.login(login);
		if(result.contains("User"))
			return "Sorry!! You are not authorized";
		else
			return result;
	}
	
	
	
	/**
	 * createAdmin() : This function will save admin instance in database
	 * @param user
	 * @return User instance
	 */
	@PostMapping("/newAdmin")
	User createAdmin(@Valid @RequestBody User admin)
	{
		logger.info("in controller-createAdmin");
		return userManager.registerUser(admin);
	}
	
	
	
	/**
	 * To fetch all user.
	 * @return List of Users
	 */
	@GetMapping("/FetchAllUsers")
	public List<User> listUsers()
	{
		logger.info("in controller-fetchAllUsers");
		return adminService.fetchAllUsers();
	}
	
	
	/**
	 * To fetch user by ID.
	 * @param UId
	 * @return User instance
	 */
	@GetMapping("/FetchUserById/{UId}")
	public User getUserById(@PathVariable int UId)
	{
		logger.info("in controller-fetchUserById");
		return adminService.getUserById(UId);
	}
}