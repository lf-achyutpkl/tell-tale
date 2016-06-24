package com.lftechnology.tell.tale.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;

import org.apache.commons.codec.digest.DigestUtils;

import com.lftechnology.tell.tale.dao.UserDao;
import com.lftechnology.tell.tale.entity.User;
import com.lftechnology.tell.tale.exception.ObjectNotFoundException;
import com.lftechnology.tell.tale.service.UserService;

/**
 * 
 * @author Prajjwal Raj Kandel<prajjwalkandel@lftechnology.com>
 *
 */

public class UserServiceImpl implements UserService {

    private String salt = "thisissamplesalt";

    @Inject
    private UserDao userDao;

    @Override
    public User save(User user) {
        String saltedPassword = salt + user.getPassword();
        user.setPassword(DigestUtils.shaHex(saltedPassword));
        return this.userDao.save(user);
    }

    @Override
    public User update(UUID id, User updatedUser) {
        User user = this.userDao.findOne(id);
        if (user == null) {
            throw new ObjectNotFoundException();
        }
        user.setEmail(updatedUser.getEmail());
        user.setName(updatedUser.getName());
        return this.userDao.update(user);
    }

    @Override
    public User findOne(UUID id) {
        return this.userDao.findOne(id);
    }

    @Override
    public List<User> findAll() {
        return this.userDao.findAll();
    }

    @Override
    public Map<String, Object> find(String start, String offset) {
        return new HashMap<String, Object>() {
            {
                put("count", userDao.count());
                put("data", userDao.find(start, offset));
            }
        };
    }

    @Override
    public void remove(User user) {
        this.userDao.remove(user);
    }

    @Override
    public void removeById(UUID id) {
        this.userDao.removeById(id);
    }
}
