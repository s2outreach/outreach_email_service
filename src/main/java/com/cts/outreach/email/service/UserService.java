package com.cts.outreach.email.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.outreach.email.entity.UserEntity;
import com.cts.outreach.email.repo.UserInterface;

@Service
public class UserService {
	
	private Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserInterface userInterface;
	
	public List<UserEntity> getUsers() {
		LOGGER.info("Number of users requested");
		return userInterface.findAll();
	}

}
