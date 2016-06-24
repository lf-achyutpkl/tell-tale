package com.lftechnology.tell.tale.service.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;

import org.apache.commons.codec.digest.DigestUtils;

import com.lftechnology.tell.tale.dao.DecryptionKeyDao;
import com.lftechnology.tell.tale.dao.UserDao;
import com.lftechnology.tell.tale.entity.Session;
import com.lftechnology.tell.tale.entity.Token;
import com.lftechnology.tell.tale.entity.User;
import com.lftechnology.tell.tale.exception.ObjectNotFoundException;
import com.lftechnology.tell.tale.exception.UnauthorizedException;
import com.lftechnology.tell.tale.service.EncryptionDecryptionService;
import com.lftechnology.tell.tale.service.JwtTokenService;
import com.lftechnology.tell.tale.service.SessionService;
import com.lftechnology.tell.tale.service.UserService;
import com.lftechnology.tell.tale.util.NumberUtil;

/**
 * 
 * @author Prajjwal Raj Kandel<prajjwalkandel@lftechnology.com>
 *
 */

public class UserServiceImpl implements UserService {

	private String salt = "thisissamplesalt";

	@Inject
	private UserDao userDao;

	@Inject
	private DecryptionKeyDao decryptionKeyDao;

	@Inject
	private EncryptionDecryptionService encryptionDecryptionService;

	@Inject
	private SessionService sessionSession;

	@Inject
	private JwtTokenService jwtTokenService;

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

	@Override
	public Token login(User userObj) {
		String saltedPassword = salt + userObj.getPassword();
		userObj.setPassword(DigestUtils.shaHex(saltedPassword));
		User user = userDao.login(userObj);
		if (user == null) {
			throw new UnauthorizedException();
		}
		String randomText = NumberUtil.getRandom();
		String encryptedDecryptionKey = decryptionKeyDao.getDecryptionKey(user).getValue();
		String decryptionKey = encryptionDecryptionService.decrypt(encryptedDecryptionKey, user.getPassword());
		String encryptedPrivateKeyWithRandomText = encryptionDecryptionService.decrypt(decryptionKey, randomText);

		// save user in session table
		LocalDateTime expiresAt = LocalDateTime.now().plusMinutes(JwtTokenServiceImpl.TOKEN_EXPIRE_AT);
		Session session = new Session(user, encryptedPrivateKeyWithRandomText, expiresAt);
		sessionSession.save(session);

		// generate jwt token for user
		Map<String, Object> tokenPayload = jwtTokenService.makePayload(randomText, JwtTokenServiceImpl.TOKEN_EXPIRE_AT);
		Token token = new Token();
		token.setToken(jwtTokenService.payloadToToken(tokenPayload));
		return token;
	}

	@Override
	public void logout(Token token) {
		// TODO Auto-generated method stub

	}
}
