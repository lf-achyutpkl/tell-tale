package com.lftechnology.tell.tale.dao;

import java.util.List;
import java.util.UUID;

import com.lftechnology.tell.tale.entity.User;

/**
 * 
 * @author Prajjwal Raj Kandel<prajjwalkandel@lftechnology.com>
 *
 */
public interface UserDao {
    public User save(User user);

    public User update(User user);

    public User findOne(UUID id);

    public List<User> findAll();

    public List<User> find(String start, String offset);

    public void remove(User user);

    public void removeById(UUID id);
    
    public long count();
    
	public User login(User user);
}