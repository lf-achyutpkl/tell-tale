package com.lftechnology.tell.tale.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.lftechnology.tell.tale.entity.Token;
import com.lftechnology.tell.tale.entity.User;

/**
 * 
 * @author Prajjwal Raj Kandel<prajjwalkandel@lftechnology.com>
 *
 */
public interface UserService {

    public User save(User user);

    public User update(UUID id, User user);

    public User findOne(UUID id);

    public List<User> findAll();

    public Map<String,Object> find(String start, String offset);

    public void remove(User user);

    public void removeById(UUID id);
    
	public Token login(User userObj);
	
	public void logout(Token token);
}
