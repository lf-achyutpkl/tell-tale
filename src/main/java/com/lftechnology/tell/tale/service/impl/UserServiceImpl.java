package com.lftechnology.tell.tale.service.impl;

import java.security.KeyPair;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.codec.digest.DigestUtils;

import com.lftechnology.tell.tale.dao.UserDao;
import com.lftechnology.tell.tale.entity.DecryptionKey;
import com.lftechnology.tell.tale.entity.EncryptionKey;
import com.lftechnology.tell.tale.entity.User;
import com.lftechnology.tell.tale.exception.EncryptionException;
import com.lftechnology.tell.tale.exception.ObjectNotFoundException;
import com.lftechnology.tell.tale.service.DecryptionKeyService;
import com.lftechnology.tell.tale.service.EncryptionDecryptionService;
import com.lftechnology.tell.tale.service.EncryptionKeyService;
import com.lftechnology.tell.tale.service.UserService;
import com.lftechnology.tell.tale.util.KeyPairUtil;

/**
 * 
 * @author Prajjwal Raj Kandel<prajjwalkandel@lftechnology.com>
 *
 */
@Stateless
public class UserServiceImpl implements UserService {

    private static final String SALT = "thisissamplesalt";

    @Inject
    private UserDao userDao;

     @Inject
     private EncryptionDecryptionService encryptionDecryptionService;

    @Inject
    private DecryptionKeyService decryptionKeyService;

    @Inject
    private EncryptionKeyService encryptionKeyService;

    @Override
    public User save(User user) {
        String password = user.getPassword();
        String saltedPassword = SALT + password;
        user.setPassword(DigestUtils.shaHex(saltedPassword));
        String encryptedDecryptionKey = "";
        String encryptionKey = "";
        try {
            KeyPair kp = KeyPairUtil.generateKeyPair();
            String decryptionKey = KeyPairUtil.savePrivateKey(kp.getPrivate());
            encryptedDecryptionKey = encryptionDecryptionService.encrypt(decryptionKey, password);
            encryptionKey = KeyPairUtil.savePublicKey(kp.getPublic());
        } catch (Exception e) {
            throw new EncryptionException();
        }
        DecryptionKey dk = new DecryptionKey();
        dk.setUser(user);
        dk.setValue(encryptedDecryptionKey);
        decryptionKeyService.save(dk);

        EncryptionKey ek = new EncryptionKey();
        ek.setUser(user);
        ek.setValue(encryptionKey);
        encryptionKeyService.save(ek);

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
