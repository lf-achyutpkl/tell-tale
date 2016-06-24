package com.lftechnology.tell.tale.service;

import com.lftechnology.tell.tale.entity.Token;
import com.lftechnology.tell.tale.entity.User;

/**
 * @author Achyut Pokhrel <achyutpokhrel@lftechnology.com>
 * Jun 24, 2016
 * 
 */
public interface AuthService {
	
	public User login(User user);
	
	public void logout(Token token);
}
