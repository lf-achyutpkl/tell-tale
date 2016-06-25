package com.lftechnology.tell.tale.service.impl;

import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.codec.digest.DigestUtils;

import com.lftechnology.tell.tale.dao.DecryptionKeyDao;
import com.lftechnology.tell.tale.dao.UserDao;
import com.lftechnology.tell.tale.entity.DecryptionKey;
import com.lftechnology.tell.tale.entity.EncryptionKey;
import com.lftechnology.tell.tale.entity.Session;
import com.lftechnology.tell.tale.entity.Token;
import com.lftechnology.tell.tale.entity.User;
import com.lftechnology.tell.tale.exception.EncryptionException;
import com.lftechnology.tell.tale.exception.ObjectNotFoundException;
import com.lftechnology.tell.tale.exception.UnauthorizedException;
import com.lftechnology.tell.tale.service.DecryptionKeyService;
import com.lftechnology.tell.tale.service.EncryptionDecryptionService;
import com.lftechnology.tell.tale.service.EncryptionKeyService;
import com.lftechnology.tell.tale.service.JwtTokenService;
import com.lftechnology.tell.tale.service.SessionService;
import com.lftechnology.tell.tale.service.UserService;
import com.lftechnology.tell.tale.util.KeyPairUtil;
import com.lftechnology.tell.tale.util.NumberUtil;

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
	private DecryptionKeyDao decryptionKeyDao;

	@Inject
	private EncryptionDecryptionService encryptionDecryptionService;

	@Inject
	private SessionService sessionService;

	@Inject
	private JwtTokenService jwtTokenService;
	
	@Inject
	private EncryptionKeyService encryptionKeyService;
	
	@Inject
	private DecryptionKeyService decryptionKeyService;



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
    
	@Override
	public Token login(User userObj) {
	    if(userObj.getEmail() == null || userObj.getPassword() == null){
	        throw new UnauthorizedException();
	    }
		String password = userObj.getPassword();
		String saltedPassword = SALT + userObj.getPassword();
		userObj.setPassword(DigestUtils.shaHex(saltedPassword));
		User user = userDao.login(userObj);
		if (user == null) {
			throw new UnauthorizedException();
		}
		String randomText = NumberUtil.getRandom();
		String encryptedDecryptionKey = decryptionKeyDao.getDecryptionKey(user).getValue();
		String decryptionKey = encryptionDecryptionService.decrypt(encryptedDecryptionKey, password);
		String toEncryptKey = "";
		try {
			PrivateKey pkey = KeyPairUtil.loadPrivateKey(decryptionKey);
			toEncryptKey = KeyPairUtil.savePrivateKey(pkey);
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String encryptedPrivateKeyWithRandomText = encryptionDecryptionService.encrypt(toEncryptKey, randomText);

		// save user in session table
		LocalDateTime expiresAt = LocalDateTime.now().plusMinutes(JwtTokenServiceImpl.TOKEN_EXPIRE_AT);
		Session session = new Session(user, encryptedPrivateKeyWithRandomText, expiresAt);
		sessionService.save(session);

		// generate jwt token for user
		Map<String, Object> tokenPayload = jwtTokenService.makePayload(user,randomText, JwtTokenServiceImpl.TOKEN_EXPIRE_AT);
		Token token = new Token();
		token.setToken(jwtTokenService.payloadToToken(tokenPayload));
		return token;
	}

	@Override
	public void logout(User user) {
		sessionService.logout(user);
	}
}
