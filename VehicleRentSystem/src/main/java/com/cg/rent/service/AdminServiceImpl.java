package com.cg.rent.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.rent.dao.UserDAO;
import com.cg.rent.model.User;

/**
 * AdminService class :
 * The class performs services related to Admin. 
 * 
 * @author sweet
 *
 */
@Service
public class AdminServiceImpl implements AdminService {
	
	private static final Logger logger = LogManager.getLogger(AdminServiceImpl.class);
	
	
	@Autowired
	UserDAO userDAO;
	
	
	/**
	 * fetchUser()
	 * This method will return list of all users
	 * @return List of User 
	 */
	public List<User> fetchAllUsers()
	{
		logger.info(" Entered service-(fetchAllUser)");
		List<User> userlist = userDAO.findAll();
		logger.info(" Completed service-(fetchAllUser)");
		return userlist;
	}

	/**
	 * getUserId() : This method will return User by Id
	 * @param UId
	 * @return User instance
	 */
	public User getUserById(int UId)
	{
		logger.info(" Entered service-(getUserById)");
		logger.info(" Completed service-(getUserById)");
		return userDAO.findById(UId).get();
	}

}
