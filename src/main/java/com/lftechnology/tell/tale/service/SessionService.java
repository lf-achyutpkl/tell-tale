package com.lftechnology.tell.tale.service;

import java.util.UUID;

import com.lftechnology.tell.tale.entity.Session;
import com.lftechnology.tell.tale.entity.User;

/**
 * 
 * @author Prajjwal Raj Kandel<prajjwalkandel@lftechnology.com>
 *
 */
public interface SessionService {

    public Session save(Session session);

    public void remove(Session session);

    public void removeById(UUID id);
    
    public void logout(User user);
    
    public Session findSession(User user);
}
