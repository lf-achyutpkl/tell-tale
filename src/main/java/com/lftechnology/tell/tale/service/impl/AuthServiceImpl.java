package com.lftechnology.tell.tale.service.impl;

import javax.ejb.Stateless;

import com.lftechnology.tell.tale.entity.Token;
import com.lftechnology.tell.tale.entity.User;
import com.lftechnology.tell.tale.service.AuthService;

/**
 * @author Achyut Pokhrel <achyutpokhrel@lftechnology.com>
 * Jun 24, 2016
 * 
 */
@Stateless
public class AuthServiceImpl implements AuthService{

	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void logout(Token token) {
		// TODO Auto-generated method stub
		
	}

}
