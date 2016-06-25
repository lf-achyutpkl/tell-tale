package com.lftechnology.tell.tale.service.impl;

import java.util.UUID;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.lftechnology.tell.tale.dao.SessionDao;
import com.lftechnology.tell.tale.entity.Session;
import com.lftechnology.tell.tale.entity.User;
import com.lftechnology.tell.tale.service.SessionService;

/**
 * @author Achyut Pokhrel <achyutpokhrel@lftechnology.com>
 * Jun 25, 2016
 * 
 */
@Stateless
public class SessionServiceImpl implements SessionService{

	@Inject
	private SessionDao sessionDao; 
	
	@Override
	public Session save(Session session) {
		return sessionDao.save(session);
	}

	@Override
	public void remove(Session session) {
		sessionDao.remove(session);
		
	}

	@Override
	public void removeById(UUID id) {
		sessionDao.removeById(id);
		
	}

	@Override
	public void logout(User user) {
		sessionDao.logout(user);
	}

	@Override
	public Session findSession(User user) {
		return sessionDao.findSession(user);
	}

}
